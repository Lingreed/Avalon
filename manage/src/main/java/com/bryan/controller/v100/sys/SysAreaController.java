package com.bryan.controller.v100.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bryan.common.constant.ApiResult;
import com.bryan.controller.BaseController;
import com.bryan.controller.v100.vo.sys.area.SysAreaQueryReq;
import com.bryan.dao.sys.domain.SysArea;
import com.bryan.v100.service.sys.SysAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:  SysAreaController
 * Function:  省市区地址生成
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/2/7
 */
@RestController
//@Scope("prototype")
@RequestMapping("/api/v100/sys/sysArea")
public class SysAreaController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysAreaController.class);

    @Resource
    private SysAreaService sysAreaService;

    @ResponseBody
    @RequestMapping(value = "/findAddress", method = RequestMethod.POST)
    public ApiResult findAddress(HttpServletRequest request, HttpServletResponse response,
                                 HttpSession session, @RequestBody SysAreaQueryReq req) {

        String address = sysAreaService.findAddress(req.getAreaCode());

        return ApiResult.newInstance().addResult(address);
    }

    @RequestMapping(value = "/createJson", method = RequestMethod.GET)
    public void createJson(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 省
            List<SysArea> provinceList = sysAreaService.findList(0, 1);
            List<Map<String, Object>> provinceMapList = new ArrayList<>();
            for (SysArea province : provinceList) {
                Map<String, Object> map = new HashMap<>();
                map.put("value", province.getAreaCode());
                map.put("label", province.getAreaName());

                //市
                List<SysArea> cityList = sysAreaService.findList(province.getId(), 2);
                List<Map<String, Object>> cityMapList = new ArrayList<>();
                for (SysArea city : cityList) {
                    Map<String, Object> cityMap = new HashMap<>();
                    cityMap.put("value", city.getAreaCode());
                    cityMap.put("label", city.getAreaName());
                    // 区
                    List<SysArea> areaList = sysAreaService.findList(city.getId(), 3);
                    List<Map<String, Object>> areaMapList = new ArrayList<>();
                    for (SysArea area : areaList) {
                        Map<String, Object> areaMap = new HashMap<>();
                        areaMap.put("value", area.getAreaCode());
                        areaMap.put("label", area.getAreaName());
                        areaMapList.add(areaMap);
                    }
                    cityMap.put("children", areaMapList);
                    cityMapList.add(cityMap);
                }
                map.put("children", cityMapList);
                provinceMapList.add(map);
            }

            String json = JSON.toJSONString(provinceMapList, SerializerFeature.DisableCircularReferenceDetect);

            File file = new File("D:/area.json");
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos);
            pw.write(json.toCharArray());
            pw.flush();
            pw.close();
        } catch (Exception e) {

        }

    }
}
