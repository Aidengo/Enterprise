package service;

import util.DBUtil;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StaffServiceImpl implements StaffService {
    @Override
    public TableDTO retrieveStaffs(StaffRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from staff ");
        if (request.getSearchKey()!=null&&!"".equals(request.getSearchKey().trim())){
            sql.append(" where name like '%"+request.getSearchKey().trim()+"%' ");
        }
        sql.append("order by id desc limit ").append(request.getStart()).append(",")
                .append(request.getPageSize());
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTO returnDTO = new TableDTO();
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            //查询记录
            returnDTO.setData(fillData(rs));
            sql.setLength(0);
            sql.append("select * from staff ");
            if (request.getSearchKey()!=null&&!"".equals(request.getSearchKey().trim())){
                sql.append(" where name like '%"+request.getSearchKey().trim()+"%' ");
            }
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while(rs.next()){
                int count = rs.getInt(1);
                returnDTO.setTotalCount(count);
            }
            return returnDTO;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }

    @Override
    public boolean add(StaffDO staffDO) {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into staff(id,name,sex,age,adept,salary) ");
        sql.append(" values(?,?,?,?,?,?) ");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,staffDO.getId());
            ps.setString(2,staffDO.getName());
            ps.setString(3,staffDO.getSex());
            ps.setInt(4,staffDO.getAge());
            ps.setString(5,staffDO.getAdept());
            ps.setDouble(6,staffDO.getSalary());
            ps.executeUpdate();
            return  true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

    @Override
    public StaffDO getById(int selectedStaffId) {
        StringBuilder sql = new StringBuilder(" select * from staff where id = ?");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StaffDO staffDO = new StaffDO();
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,selectedStaffId);
            rs = ps.executeQuery();
            //处理查出的每一条结果
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                String adept = rs.getString("adept");
                double salary = rs.getDouble("salary");
                staffDO.setId(id);
                staffDO.setName(name);
                staffDO.setSex(sex);
                staffDO.setAge(age);
                staffDO.setAdept(adept);
                staffDO.setSalary(salary);
            }
            return staffDO;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }

    @Override
    public boolean update(StaffDO staffDO) {
        StringBuilder sql = new StringBuilder();
        sql.append(" update staff set id=?,name=?,sex=?,age=?,adept=?,salary=? ");
        sql.append(" where id =? ");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,staffDO.getId());
            ps.setString(2,staffDO.getName());
            ps.setString(3,staffDO.getSex());
            ps.setInt(4,staffDO.getAge());
            ps.setString(5,staffDO.getAdept());
            ps.setDouble(6,staffDO.getSalary());
            ps.setInt(7,staffDO.getId());
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

    @Override
    public boolean delete(int[] selectedStaffIds) {
        StringBuilder sql = new StringBuilder();
        sql.append(" delete from staff where id in ( ");
        int length = selectedStaffIds.length;
        for(int i=0;i<selectedStaffIds.length;i++){
            if (i==(selectedStaffIds.length-1)){
                sql.append("?");
            }else{
                sql.append("?,");
            }
        }
        sql.append(" ) ");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            for(int i=0;i<selectedStaffIds.length;i++){
                //设置参数，从1开始
                ps.setInt(i+1,selectedStaffIds[i]);
            }
            return ps.executeUpdate() == length;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

    private static Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();
        //处理查出的每一条结果
        while(rs.next()) {
            Vector<Object> oneRecord  = new Vector<>();
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String sex = rs.getString("sex");
            int age = rs.getInt("age");
            String adept = rs.getString("adept");
            double salary = rs.getDouble("salary");
            oneRecord.addElement(id);
            oneRecord.addElement(name);
            oneRecord.addElement(sex);
            oneRecord.addElement(age);
            oneRecord.addElement(adept);
            oneRecord.addElement(salary);
            data.addElement(oneRecord);
        }
        return data;
    }
}
