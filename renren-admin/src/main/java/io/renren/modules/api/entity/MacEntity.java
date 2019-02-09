package io.renren.modules.api.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Objects;

/**
 * 药柜设备表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-28 16:27:32
 */
@TableName("tb_mac")
public class MacEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 机器唯一id
	 */
	@TableId(type = IdType.INPUT)
	private String machineNo;
	/**
	 * 机器简称
	 */
	private String machineShortName;
	/**
	 * 机器地址
	 */
	private String machineAdress;
	/**
	 * 设备经度
	 */
	private String machineLongitude;
	/**
	 * 设备纬度
	 */
	private String machineLatitude;
	/**
	 * 设备状态
	 */
	private Boolean machineStatus;
	/**
	 * 客户名称
	 */
	private String clientName;

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
	/**
	 * 设置：机器地址
	 */
	public void setMachineAdress(String machineAdress) {
		this.machineAdress = machineAdress;
	}
	/**
	 * 获取：机器地址
	 */
	public String getMachineAdress() {
		return machineAdress;
	}
	/**
	 * 设置：设备经度
	 */
	public void setMachineLongitude(String machineLongitude) {
		this.machineLongitude = machineLongitude;
	}
	/**
	 * 获取：设备经度
	 */
	public String getMachineLongitude() {
		return machineLongitude;
	}
	/**
	 * 设置：设备纬度
	 */
	public void setMachineLatitude(String machineLatitude) {
		this.machineLatitude = machineLatitude;
	}
	/**
	 * 获取：设备纬度
	 */
	public String getMachineLatitude() {
		return machineLatitude;
	}
	/**
	 * 设置：设备状态
	 */
	public void setMachineStatus(Boolean machineStatus) {
		this.machineStatus = machineStatus;
	}
	/**
	 * 获取：设备状态
	 */
	public Boolean getMachineStatus() {
		return machineStatus;
	}

	/**
	 * 客户名称
	 */
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MacEntity macEntity = (MacEntity) o;
		return Objects.equals(machineNo, macEntity.machineNo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(machineNo, machineShortName, machineAdress, machineLongitude, machineLatitude, machineStatus,clientName);
	}

	@Override
	public String toString() {
		return "MacEntity{" +
				"machineNo='" + machineNo + '\'' +
				", machineShortName='" + machineShortName + '\'' +
				", machineAdress='" + machineAdress + '\'' +
				", machineLongitude='" + machineLongitude + '\'' +
				", machineLatitude='" + machineLatitude + '\'' +
				", machineStatus=" + machineStatus +
				", clientName='" + clientName + '\'' +
				'}';
	}
}
