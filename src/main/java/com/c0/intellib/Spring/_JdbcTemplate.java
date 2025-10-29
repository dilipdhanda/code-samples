package com.c0.intellib.Spring;

/*
= ORIGINAL
public class HwDeviceDao {
    private DataSource dsHwDevices;
    public void setDataSource(DataSource dsHwDevices) {
        this.dsHwDevices = dsHwDevices;
    }
    public void insertHwDevices(HardwareDevices device) {
        JdbcTemplate jdbcHwDevices = new JdbcTemplate(dsHwDevices);
        jdbcHwDevices.update(insertDevice,
            new Object[] {device.getDeviceId(), device.getDeviceModel()});
    }
}

= CORRECTED (using JdbcDaoSupport)
import org.springframework.jdbc.core.support.JdbcDaoSupport;
public class HwDeviceDao extends JdbcDaoSupport {
    public void insertHwDevices(HardwareDevices device) {
        getJdbcTemplate().update(insertDevice,
            new Object[] {device.getDeviceId(), device.getDeviceModel()});
    }
}
✅ Changes:
❌ Remove the setDataSource() method (Spring’s JdbcDaoSupport already provides one internally).
❌ Remove manual JdbcTemplate creation.
✅ Use getJdbcTemplate() provided by JdbcDaoSupport.

 */
public class _JdbcTemplate {
}
