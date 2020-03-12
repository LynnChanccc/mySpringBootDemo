package com.woniu.springboot.util;

import com.google.common.base.CaseFormat;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cl
 * @Date 2020/3/9 13:44
 * mybatis更新对象工具类
 */
public class SimpleUpdateObjectUtil extends XMLLanguageDriver implements LanguageDriver {

    private final Pattern pattern = Pattern.compile("\\(#\\{(\\w+)\\}\\)");

    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
        Matcher matcher = pattern.matcher(script);
        if (matcher.find()) {
            StringBuilder sb = new StringBuilder();
            sb.append("<set>");
            for (Field field : parameterType.getDeclaredFields()) {
                String temp = "< if test =\"_filed != null\">_column=#{_filed},</if>";
                sb.append(temp.replaceAll("_filed", field.getName().replaceAll("_column", CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName()))));
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append("</set>");
            script = matcher.replaceAll(sb.toString());
            script = "<script>" + script + "</script>";
        }
        return super.createSqlSource(configuration, script, parameterType);
    }
}
