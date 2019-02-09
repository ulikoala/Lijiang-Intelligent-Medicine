package io.renren.modules.api.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-28 16:27:32
 */
@TableName("tb_macpos")
public class MacPosEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 机器唯一id
	 */
	@TableId
	private String machineNo;
	/**
	 * 药品批号No
	 */
	private String drugBatchNo;
	/**
	 * 此货道最大数量
	 */
	private String maxNumber;
	/**
	 * 此货道当前数量
	 */
	private String nowNumber;
	/**
	 * 货道号
	 */
	private String positionCode;
	/**
	 * 此货道状态
	 */
	private String positionState;
	/**
	 * 机器简称
	 */
	private String machineShortName;

	/**
	 * 设置：机器唯一id
	 */
	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}
	/**
	 * 获取：机器唯一id
	 */
	public String getMachineNo() {
		return machineNo;
	}
	/**
	 * 设置：药品批号No
	 */
	public void setDrugBatchNo(String drugBatchNo) {
		this.drugBatchNo = drugBatchNo;
	}
	/**
	 * 获取：药品批号No
	 */
	public String getDrugBatchNo() {
		return drugBatchNo;
	}
	/**
	 * 设置：此货道最大数量
	 */
	public void setMaxNumber(String maxNumber) {
		this.maxNumber = maxNumber;
	}
	/**
	 * 获取：此货道最大数量
	 */
	public String getMaxNumber() {
		return maxNumber;
	}
	/**
	 * 设置：此货道当前数量
	 */
	public void setNowNumber(String nowNumber) {
		this.nowNumber = nowNumber;
	}
	/**
	 * 获取：此货道当前数量
	 */
	public String getNowNumber() {
		return nowNumber;
	}
	/**
	 * 设置：货道号
	 */
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	/**
	 * 获取：货道号
	 */
	public String getPositionCode() {
		return positionCode;
	}
	/**
	 * 设置：此货道状态
	 */
	public void setPositionState(String positionState) {
		this.positionState = positionState;
	}
	/**
	 * 获取：此货道状态
	 */
	public String getPositionState() {
		return positionState;
	}
	/**
	 * 设置：机器简称
	 */
	public void setMachineShortName(String machineShortName) {
		this.machineShortName = machineShortName;
	}
	/**
	 * 获取：机器简称
	 */
	public String getMachineShortName() {
		return machineShortName;
	}
}
