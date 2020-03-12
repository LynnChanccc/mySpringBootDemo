package com.woniu.springboot.util;

import com.google.common.base.CaseFormat;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cl
 * @Date 2020/3/9 13:09
 * mybatis插入对象工具类
 */
public class SimpleInsertObjectUtil extends XMLLanguageDriver implements LanguageDriver {

    private final Pattern pattern = Pattern.compile("\\(#\\{(\\w+)\\}\\)");

    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
        Matcher matcher = pattern.matcher(script);
        if (matcher.find()){ //find()是部分匹配 matches()是完全匹配
            StringBuilder sb = new StringBuilder();
            StringBuilder temp = new StringBuilder();
            sb.append("(");
            for (Field field:parameterType.getDeclaredFields()){
                //和数据库字段名保持一致
                sb.append(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,field.getName())+",");
                //和实体属性名保持一致
                temp.append("#{"+field.getName()+"},");
            }

            sb.deleteCharAt(sb.lastIndexOf(","));
            temp.deleteCharAt(temp.lastIndexOf(","));
            sb.append(") values ("+temp.toString()+")");
            script = matcher.replaceAll(sb.toString());
            script = "<script>"+script+"</script>";
        }
        return super.createSqlSource(configuration, script, parameterType);
    }
}
