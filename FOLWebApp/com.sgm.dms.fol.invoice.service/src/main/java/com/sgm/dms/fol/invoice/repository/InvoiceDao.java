package com.sgm.dms.fol.invoice.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sgm.dms.fol.common.api.exception.ServiceAppException;
import com.sgm.dms.fol.invoice.dto.InvoiceDto;
import com.sgm.dms.fol.invoice.dto.InvoiceFollowDTO;
import com.sgm.dms.fol.invoice.dto.SapReturnItemsDTO;
import com.sgm.dms.fol.invoice.dto.WrOrderDTO;

public interface InvoiceDao {

    /**
     * 
     *
     * @author Lujinglei
     * description 查询索赔发票所有信息
     * @date 2016年5月9日
     * @param invoiceFollowDTO
     * @return
     * @throws ServiceAppException
     */
   public List<InvoiceFollowDTO> getInvoiceFollow(InvoiceFollowDTO invoiceFollowDTO) throws SQLException;
   /**
    * 
    *
    * @author Lujinglei
    * description 索赔发票明细查询
    * @date 2016年5月13日
    * @param invoiceFollowDTO
    * @return
    * @throws SQLException
    */
   public List<InvoiceFollowDTO> getInvoiceDeatil(@Param("invoiceNo")String invoiceNo, @Param("sapCode")String sapCode) throws SQLException;
   
   /**
    * 
    *
    * @author Lujinglei
    * description 作废
    * @date 2016年5月9日
    * @param invoiceFollowDTO
    * @return
    * @throws ServiceAppException
    */
   public int cancelInvoice(InvoiceFollowDTO invoiceFollowDTO) throws SQLException;
   /**
    * 
    *
    * @author Lujinglei
    * description 重做
    * @date 2016年5月9日
    * @param invoiceFollowDTO
    * @return
    * @throws ServiceAppException
    */
   public int anewInvoice(InvoiceFollowDTO invoiceFollowDTO) throws SQLException;
   
   
   /**
    * 
    *
    * @author Lujinglei
    * description FOL-SAP接口状态查询
    * @date 2016年5月10日
    * @return
    * @throws SQLException
    */
   public List<SapReturnItemsDTO> getFOLtoSAPInterfaceStatus(SapReturnItemsDTO sapReturnItemsDTO) throws SQLException;
   
   /**
    * 
    *
    * @author Lujinglei
    * description SAP每日财务凭证查询
    * @date 2016年5月10日
    * @return
    * @throws SQLException
    */
   public List<InvoiceFollowDTO> getSAPFinancialCertificate(InvoiceFollowDTO invoiceFollowDTO) throws SQLException;
   
   /**
    * 
    *
    * @author Lujinglei
    * description 经销商提交超时未处理发票查询
    * @date 2016年5月10日
    * @return
    * @throws SQLException
    */
   public List<InvoiceFollowDTO> getOvertimeUnDealInvoice(InvoiceFollowDTO invoiceFollowDTO) throws SQLException;
   
   /**
    * 
    * @author wangfl
    * 根据tsId获取发票列表
    * @date 2016年5月17日
    * @param tsId 多个tsId用逗号分隔
    * @return
    */
   List<InvoiceDto> getInvoiceByTsId(@Param("tsId") String tsId, @Param("sapCode")String sapCode);
   List<InvoiceDto> getInvoiceByInvoiceNo(@Param("invoiceNo") String invoiceNo, @Param("sapCode")String sapCode);
   
   /**
    * 
    * @author wangfl
    * TODO description
    * @date 2016年5月17日
    * @param tsId 多个tsId用逗号分开
    * @param logonId 上报人logonId
    */
   void insertInvoiceListInReport(@Param("tsIdArray") String[] tsIdArray, @Param("dto") InvoiceDto dto);
   
   /**
    * 
    * @author wangfl
    * 分页查询发票
    * @date 2016年5月19日
    * @param dto
    * @return
    */
   List<InvoiceDto> findPageList(InvoiceDto dto);
   List<InvoiceDto> findExpressPageList(InvoiceDto dto);
   
   /**
	 * 
	 * @author wangfl
	 * 根据条件查询发票总数
	 * @date 2016年5月19日
	 * @param dto
	 * @return
	 */
   int findListTotalNum(InvoiceDto dto);
   int findExpressListTotalNum(InvoiceDto dto);
   
   /**
    * 
    * @author wangfl
    * 发票审核时状态修改
    * @date 2016年5月19日
    * @param status
    * @param approveMan
    * @return
    */
   int updateInvoiceStatus(@Param("invoiceNo") String invoiceNo,@Param("sapCode") String sapCode, @Param("status") int status, @Param("approveMan") Long approveMan, @Param("approveMsg") String approveMsg);
   
   /**
    * 
    * @author wangfl
    * 保存备注
    * @date 2016年5月20日
    * @param invoiceNo
    * @param remark
    * @param logonId
    */
   void saveInvoiceRemark(@Param("invoiceNo") String invoiceNo,@Param("sapCode") String sapCode, @Param("remark") String remark, @Param("logonId") Long logonId);
   
   /**
    * 
    * @author wangfl
    * 发票明细
    * @date 2016年5月20日
    * @param invoiceNo
    * @return
    */
   List<WrOrderDTO> findInvoiceDetailList(@Param("invoiceNo") String invoiceNo,@Param("sapCode") String sapCode);
}