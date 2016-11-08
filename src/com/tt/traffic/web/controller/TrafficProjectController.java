package com.tt.traffic.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tt.traffic.common.util.Const;
import com.tt.traffic.common.util.DateUtil;
import com.tt.traffic.domain.model.TrafficProject;
import com.tt.traffic.domain.model.User;
import com.tt.traffic.service.TrafficProjectService;
import com.tt.traffic.service.UserService;

@Controller
public class TrafficProjectController  extends ApplicationController<TrafficProject> {
	 @Resource
	 private TrafficProjectService trafficProjectService;
	 @Resource
	 private UserService userService;
	 @RequestMapping(value="/getTrafficProjectJSONArray",method = RequestMethod.POST)
	 @ResponseBody
	 public Map<String, Object> getTrafficProjectJson(HttpServletResponse response,HttpServletRequest request,@RequestBody String traffic){
		 Map<String, Object> json = new HashMap<String, Object>();
		 HttpSession session = request.getSession(false);
		 if(null!=session){
			 User user = (User)session.getAttribute(Const.SESSION_USER);
			 if(null!=user && null!=user.getId()){
				 JSONObject params = JSONObject.parseObject(traffic);
				 String name = params.getString("name");
				 String background = params.getString("background");
				 String need_goal = params.getString("need_goal");
//				 try{
////					 if(null!=name&&null!=background&&null!=need_goal){
////						 name=new String(name.getBytes("iso-8859-1"),"utf-8");
////						 background=new String(background.getBytes("iso-8859-1"),"utf-8");
////						 need_goal=new String(need_goal.getBytes("iso-8859-1"),"utf-8");
////					 }
//				 }catch(UnsupportedEncodingException e){
//					 e.printStackTrace();
//				 }
						 TrafficProject tp = new TrafficProject();
						 tp.setUser_id(user.getId());
						 tp.setProjectName(name);
						 tp.setProBackground(background);
						 tp.setNeed_goals(need_goal);
						 tp.setCreateTime(DateUtil.getCurrentDateString());
						 tp.setUpdateTime(DateUtil.getCurrentDateString());
						 tp.setYn(Const.YN_STATUS);
				Integer result = trafficProjectService.insert(tp);
				Integer proId=trafficProjectService.queryTrafficIdByName(name);
				if(null!=result && result>0){
	    			json.put("message_status","1");//插入记录成功
	    			json.put("proId",proId);
				}
			 }
		 }
		 return json;
	 }
	 /**
		 * 查询全部的项目
		 * @param request
		 * @param response
		 * @return
		 */
	    @RequestMapping(value="queryAllList")
	    @ResponseBody
	    public Map<String, Object>  queryAllList(HttpServletRequest request,
				HttpServletResponse response){
	    	Map<String, Object> json = new HashMap<String, Object>();
			List<TrafficProject> list = trafficProjectService.queryTrafficProjectAll();
			if( null!=list && list.size()>0 ) {
				json.put("projectList", list);
			}
	    	return json;
	    }
	    /**
	     * 删除项目
	     * @param request
	     * @param response
	     * @param traffic
	     * @return
	     */
	    @RequestMapping(value="/deleteTrafficProject",method = RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object>  deleteTrafficProject(HttpServletRequest request,
				HttpServletResponse response,@RequestBody String traffic){
	    	 JSONObject params = JSONObject.parseObject(traffic);
			 String proId = params.getString("proId");
	    	Map<String, Object> json = new HashMap<String, Object>();
	    	TrafficProject tp =new TrafficProject();
	    	tp.setId(Integer.parseInt(proId));
			int result=trafficProjectService.delete(tp);
			if( 0!=result) {
				json.put("message_status","1");//删除成功
				
			}
	    	return json;
	    }
	    /**
	     * 
	     * @param request
	     * @param response
	     * @param traffic
	     * @return
	     */
	    @RequestMapping(value="/editTrafficProject",method = RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object>  editTrafficProject(HttpServletRequest request,
				HttpServletResponse response,@RequestBody String traffic){
	    	 JSONObject params = JSONObject.parseObject(traffic);
			 String proId = params.getString("proId");
	    	Map<String, Object> json = new HashMap<String, Object>();
	    	TrafficProject t = trafficProjectService.queryTrafficProjectById(Integer.parseInt(proId));
			if(t!=null) {
				json.put("message_status","1");
				json.put("trafficProject", t);
			}
	    	return json;
	    }
	    /**
	     * 修改项目
	     * @param request
	     * @param response
	     * @param traffic
	     * @return
	     */
	    @RequestMapping(value="/updateTrafficProject",method = RequestMethod.POST)
	    @ResponseBody
	    public Map<String, Object>  updateTrafficProject(HttpServletRequest request,
				HttpServletResponse response,@RequestBody String traffic){
	    	 Map<String, Object> json = new HashMap<String, Object>();
			 HttpSession session = request.getSession(false);
			 if(null!=session){
				 User user = (User)session.getAttribute(Const.SESSION_USER);
				 if(null!=user && null!=user.getId()){
					 JSONObject params = JSONObject.parseObject(traffic);
					 String name = params.getString("name");
					 String background = params.getString("background");
					 String need_goal = params.getString("need_goal");
					 String proId = params.getString("proId");
							 TrafficProject tp = new TrafficProject();
							 tp.setId(Integer.parseInt(proId));
							 tp.setUser_id(user.getId());
							 tp.setProjectName(name);
							 tp.setProBackground(background);
							 tp.setNeed_goals(need_goal);
							 tp.setCreateTime(DateUtil.getCurrentDateString());
							 tp.setUpdateTime(DateUtil.getCurrentDateString());
							 tp.setYn(Const.YN_STATUS);
					Integer result = trafficProjectService.update(tp);
					if(null!=result && result>0){
		    			json.put("message_status","1");//修改成功
					}
				 }
			 }
			 return json;
	    }
	    
}
