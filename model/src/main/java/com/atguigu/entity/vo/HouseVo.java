package com.atguigu.entity.vo;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class HouseVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String communityName;       //小区名
	private Long id;                     //id
	private String name;                 //名字
	private String buildArea;             //建筑区域       hse_house
	private BigDecimal totalPrice;       //总价格           hse_house
	private BigDecimal unitPrice;          //单位价格      hse_house
	private Long houseTypeId;             //房屋类型ID       hse_house
	private Long floorId;                  //地板id        hse_house
	private Long directionId;             //方位id        hse_house
	private String defaultImageUrl;        //默认图片url    hse_house
	private Date createTime;             //创建时间
	private String houseTypeName;          //房屋类型名称
	private String floorName;             //地板名称
	private String directionName;         //方位名称

	public String getCreateTimeString() {
		Date date = this.getCreateTime();
		if(null == date) {
			return "";
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = df.format(date);
		return dateString;
	}
}

