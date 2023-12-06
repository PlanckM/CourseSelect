package com.guet.courseselecthelper.mapper;
import com.guet.courseselecthelper.demos.entity.Student;
import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import org.apache.hadoop.hbase.client.Result;
/**
 * @author Kevin
 */
public class StudentIdMapper implements RowMapper<String> {
    private static final byte[] SINFO_FAMILY = "SInfo".getBytes();
    private static final byte[] STUDENT_ID = "studentId".getBytes();

    @Override
    public String mapRow(Result result, int i) throws Exception {
        // 仅获取 studentId 列的值
        byte[] studentIdValue = result.getValue(SINFO_FAMILY, STUDENT_ID);

        // 如果 studentIdValue 不为 null，则将其转换为字符串返回
        return (studentIdValue != null) ? new String(studentIdValue) : null;
    }
}
