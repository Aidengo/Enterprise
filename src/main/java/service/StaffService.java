package service;

public interface StaffService {
    TableDTO retrieveStaffs(StaffRequest request);

    boolean add(StaffDO staffDO);

    StaffDO getById(int selectedStaffId);

    boolean update(StaffDO staffDO);

    boolean delete(int[] selectedStaffIds);
}
