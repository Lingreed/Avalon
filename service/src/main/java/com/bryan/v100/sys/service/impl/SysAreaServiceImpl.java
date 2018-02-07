package com.bryan.v100.sys.service.impl;

import com.bryan.common.base.BaseMapper;
import com.bryan.common.base.BaseServiceImpl;
import com.bryan.sys.domain.SysArea;
import com.bryan.sys.mapper.SysAreaMapper;
import com.bryan.v100.sys.service.SysAreaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/7
 */
@Service(value="sysAreaService")
@Transactional
public class SysAreaServiceImpl extends BaseServiceImpl<SysArea> implements SysAreaService {

    @Resource
    private SysAreaMapper sysAreaMapper;

    @Override
    public BaseMapper<SysArea> getMapper() {
        return sysAreaMapper;
    }

    @Override
    public String findAddress(String areaCode) {
        Example example1 = new Example(SysArea.class);
        Example.Criteria name1Param = example1.createCriteria();
        name1Param.andEqualTo("areaCode",areaCode.substring(0,2)+"0000");
        SysArea sysArea1 = super.selectOneByExample(example1);
        String address = sysArea1.getAreaName();

        Example example2 = new Example(SysArea.class);
        Example.Criteria name2Param = example2.createCriteria();
        name2Param.andEqualTo("areaCode",areaCode.substring(0,4)+"00");
        SysArea sysArea2 = super.selectOneByExample(example2);
        address += sysArea2.getAreaName();

        Example example3 = new Example(SysArea.class);
        Example.Criteria name3Param = example3.createCriteria();
        name3Param.andEqualTo("areaCode",areaCode);
        SysArea sysArea3 = super.selectOneByExample(example3);
        address += sysArea3.getAreaName();

        return address;
    }

    @Override
    public List<SysArea> findList(Integer pid, Integer level) {
        SysArea area = new SysArea();
        area.setPid(pid);
        area.setAreaLevel(level);
        return sysAreaMapper.select(area);
    }

    @Override
    public String findNameByCode(String code) {
        return null;
    }
}
