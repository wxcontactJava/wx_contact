package com.wx.contact;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wx.contact.domain.project.FloorInfo;
import com.wx.contact.domain.project.Project;
import com.wx.contact.reporsitory.project.FloorInfoRepository;
import com.wx.contact.reporsitory.project.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ContactApplicationTests {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private FloorInfoRepository floorInfoRepository;

    /**
     * 获取项目名称
     */
    @Test
    public void contextLoads() {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<>(headers);
        String result = new RestTemplate().postForObject("https://www.xzhouse.com.cn/house/mobile/item/getItems.do", formEntity, String.class);
        log.info("获取所有项目名称：{}", result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        List<JSONObject> list = JSONObject.parseArray(jsonObject.getString("obj"), JSONObject.class);
        List<Project> projectList = new ArrayList<>();
        list.forEach(bean -> {
            projectList.add(Project.builder().id(bean.getString("id")).name(bean.getString("xmmc")).tgmc(bean.getString("tgmc")).build());
//            insertFloorInfo(bean.getString("id"));
//            return;
        });
        projectRepository.saveAll(projectList);

    }

    @Test
    public void testOne() {
        insertFloorInfoLeave1("40286081630af2f601630afd9d470009");
    }

    /**
     * 获取楼号
     *
     * @param projectId
     */
    private void insertFloorInfoLeave1(String projectId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        StringBuilder data = new StringBuilder();
        data.append("itemid=").append(projectId);
        log.info("请求参数：{}", data.toString());
        HttpEntity<String> formEntity = new HttpEntity<>(data.toString(), headers);
        String result = new RestTemplate().postForObject("https://www.xzhouse.com.cn/house/mobile/building/getBuildingInfoAllByItemId.do", formEntity, String.class);
        log.info("获取楼号信息：{}", result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        List<JSONObject> list = JSONObject.parseArray(jsonObject.getString("obj"), JSONObject.class);
        List<FloorInfo> floorInfos = new ArrayList<>();
        list.forEach(bean -> {
//            floorInfos.add(FloorInfo.builder().id(bean.getString("buildingInfoID")).name(bean.getString("buildingName")).projectId(projectId).pId(id).level((byte) 1).build());
            insertFloorInfoLeave2(bean.getString("buildingInfoID"), projectId);
            try {
                Thread.sleep(10000);
                log.info("十秒请求一次");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
//        floorInfoRepository.saveAll(floorInfos);
    }

    /**
     * 获取单元号
     *
     * @param buildingId
     * @param projectId
     */
    private void insertFloorInfoLeave2(String buildingId, String projectId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        StringBuilder data = new StringBuilder();
        data.append("buildingId=").append(buildingId);
        log.info("请求参数：{}", data.toString());
        HttpEntity<String> formEntity = new HttpEntity<>(data.toString(), headers);
        String result = new RestTemplate().postForObject("https://www.xzhouse.com.cn/house/mobile/houseInfo/getHouseUnitNoByBuildingID.do", formEntity, String.class);
        log.info("获取楼号信息：{}", result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        List<String> list = JSONObject.parseArray(jsonObject.getString("obj"), String.class);
        List<FloorInfo> floorInfos = new ArrayList<>();
        list.forEach(bean -> floorInfos.add(FloorInfo.builder().id(generateId()).name(bean).
                projectId(projectId).pId(buildingId).level((byte) 2).build()));
        floorInfoRepository.saveAll(floorInfos);
    }

    @Test
    public void insertFloorInfoLeave3() {
        List<FloorInfo> floorInfoList = floorInfoRepository.findByLevelEquals((byte) 2);
        floorInfoList.forEach(floorInfo -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8"));
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            StringBuilder data = new StringBuilder();
            data.append("unitNo=").append(floorInfo.getName()).append("&buildingId=").append(floorInfo.getPId());
            log.info("请求参数：{}", data.toString());
            HttpEntity<String> formEntity = new HttpEntity<>(data.toString(), headers);
            String result = new RestTemplate().postForObject("https://www.xzhouse.com.cn/house/mobile/houseInfo/getHouseNoIdByBidUno.do", formEntity, String.class);
            log.info("获取房号信息：{}", result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            List<JSONObject> list = JSONObject.parseArray(jsonObject.getString("obj"), JSONObject.class);

            List<FloorInfo> floorInfos = new ArrayList<>();
            list.forEach(bean -> floorInfos.add(FloorInfo.builder().id(bean.getString("HouseID")).name(bean.getString("HouseNo")).
                    projectId("40286081630af2f601630afd9d470009").pId(floorInfo.getId()).level((byte) 3).build()));
            try {
                floorInfoRepository.saveAll(floorInfos);
                Thread.sleep(10000);
                log.info("十秒请求一次");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    @Test
    public void test() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        StringBuilder data = new StringBuilder();
        data.append("unitNo=").append("2").append("&buildingId=").append("A71B5824-CF02-44EA-8F28-7CCC6D37133F");
        log.info("请求参数：{}", data.toString());
        HttpEntity<String> formEntity = new HttpEntity<>(data.toString(), headers);
        String result = new RestTemplate().postForObject("https://www.xzhouse.com.cn/house/mobile/houseInfo/getHouseNoIdByBidUno.do", formEntity, String.class);
        log.info("获取房号信息：{}", result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        log.info("获取房号信息：{}", jsonObject);
    }

    public static synchronized String generateId() {
        Random random = new Random();
        Integer number = random.nextInt(90000) + 10000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

}
