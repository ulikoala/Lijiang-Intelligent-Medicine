package io.renren.modules.api.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * 药品销售表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-28 16:27:32
 */
@TableName("tb_salerecord")
public class SaleRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单号
	 */
	@TableId
	private String orderNo;
	/**
	 * 订单状态
	 */
	private Boolean orderState;
	/**
	 * 出药时间
	 */
	private String outDrugTime;
	/**
	 * 付款方式
	 */
	private String payType;
	/**
	 * 应出药数量
	 */
	private Integer shouldOutCount;
	/**
	 * 实际出药数量
	 */
	private Integer realOutCount;
	/**
	 * 药品单价
	 */
	private BigDecimal outDrugPrice;
	/**
	 * 出药总价
	 */
	private BigDecimal outDrugAllPrice;
	/**
	 * 药品名称
	 */
	private String drugName;
	/**
	 * 药品批号
	 */
	private String drugBatch;
	/**
	 * 药品唯一id
	 */
	private String drugNo;
	/**
	 * 批号唯一id
	 */
	private String drugBatchNo;
	/**
	 * 出药状态
	 */
	private String outDrugResult;
	/**
	 * 售药机简称
	 */
	private String machineShortName;
	/**
	 * 客户名称
	 */
	private String clientName;
	/**
	 * 货道号
	 */
	private String positionCode;

	/**
	 * 设置：订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：订单号
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：订单状态
	 */
	public void setOrderState(Boolean orderState) {
		this.orderState = orderState;
	}
	/**
	 * 获取：订单状态
	 */
	public Boolean getOrderState() {
		return orderState;
	}
	/**
	 * 设置：出药时间
	 */
	public void setOutDrugTime(String outDrugTime) {
		this.outDrugTime = outDrugTime;
	}
	/**
	 * 获取：出药时间
	 */
	public String getOutDrugTime() {
		return outDrugTime;
	}
	/**
	 * 设置：付款方式
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * 获取：付款方式
	 */
	public String getPayType() {
		return payType;
	}
	/**
	 * 设置：应出药数量
	 */
	public void setShouldOutCount(Integer shouldOutCount) {
		this.shouldOutCount = shouldOutCount;
	}
	/**
	 * 获取：应出药数量
	 */
	public Integer getShouldOutCount() {
		return shouldOutCount;
	}
	/**
	 * 设置：实际出药数量
	 */
	public void setRealOutCount(Integer realOutCount) {
		this.realOutCount = realOutCount;
	}
	/**
	 * 获取：实际出药数量
	 */
	public Integer getRealOutCount() {
		return realOutCount;
	}
	/**
	 * 设置：药品单价
	 */
	public void setOutDrugPrice(BigDecimal outDrugPrice) {
		this.outDrugPrice = outDrugPrice;
	}
	/**
	 * 获取：药品单价
	 */
	public BigDecimal getOutDrugPrice() {
		return outDrugPrice;
	}
	/**
	 * 设置：出药总价
	 */
	public void setOutDrugAllPrice(BigDecimal outDrugAllPrice) {
		this.outDrugAllPrice = outDrugAllPrice;
	}
	/**
	 * 获取：出药总价
	 */
	public BigDecimal getOutDrugAllPrice() {
		return outDrugAllPrice;
	}
	/**
	 * 设置：药品名称
	 */
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	/**
	 * 获取：药品名称
	 */
	public String getDrugName() {
		return drugName;
	}
	/**
	 * 设置：药品批号
	 */
	public void setDrugBatch(String drugBatch) {
		this.drugBatch = drugBatch;
	}
	/**
	 * 获取：药品批号
	 */
	public String getDrugBatch() {
		return drugBatch;
	}
	/**
	 * 设置：药品唯一id
	 */
	public void setDrugNo(String drugNo) {
		this.drugNo = drugNo;
	}
	/**
	 * 获取：药品唯一id
	 */
	public String getDrugNo() {
		return drugNo;
	}
	/**
	 * 设置：批号唯一id
	 */
	public void setDrugBatchNo(String drugBatchNo) {
		this.drugBatchNo = drugBatchNo;
	}
	/**
	 * 获取：批号唯一id
	 */
	public String getDrugBatchNo() {
		return drugBatchNo;
	}
	/**
	 * 设置：出药状态
	 */
	public void setOutDrugResult(String outDrugResult) {
		this.outDrugResult = outDrugResult;
	}
	/**
	 * 获取：出药状态
	 */
	public String getOutDrugResult() {
		return outDrugResult;
	}
	/**
	 * 设置：售药机简称
	 */
	public void setMachineShortName(String machineShortName) {
		this.machineShortName = machineShortName;
	}
	/**
	 * 获取：售药机简称
	 */
	public String getMachineShortName() {
		return machineShortName;
	}
	/**
	 * 设置：客户名称
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	/**
	 * 获取：客户名称
	 */
	public String getClientName() {
		return clientName;
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
}
