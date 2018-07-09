package com.bryan.v100.service.sys;

import com.bryan.common.base.BaseService;
import com.bryan.dao.sys.domain.SysArea;

import java.util.List;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/7
 */
public interface SysAreaService extends BaseService<SysArea> {
    String findAddress(String areaCode);

    /**
     * @param pid
     * @param level
     * @return
     * @Title: findList
     * @Description: 查询省市区列表
     */
    List<SysArea> findList(Integer pid, Integer level);

    /**
     * @param code
     * @return
     * @Title: findNameByCode
     * @Description: 根据code查询名称
     */
    String findNameByCode(String code);
}
