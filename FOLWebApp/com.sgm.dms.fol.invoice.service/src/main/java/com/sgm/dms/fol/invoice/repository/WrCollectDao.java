package com.sgm.dms.fol.invoice.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sgm.dms.fol.invoice.domain.WrCollect;
import com.sgm.dms.fol.invoice.dto.InvoiceFollowDTO;
import com.sgm.dms.fol.invoice.dto.StayInvoiceDTO;
import com.sgm.dms.fol.invoice.dto.WrCollectDTO;

public interface WrCollectDao {

    int insert(WrCollect record);
    
    public List<WrCollectDTO> getWrCollectRecord(InvoiceFollowDTO invoiceFollowDTO) throws SQLException;
    
    public List<WrCollectDTO> getWrCollectByTsId(@Param("tsId")String tsId);
    /**
     * 
     *
     * @author Lujinglei
     * description 将汇总的发票置于无效
     * @date 2016年5月13日
     * @param wrCollectDTO
     * @return
     * @throws SQLException
     */
    public int deleteInvoice(String invoiceNo) throws SQLException;


    /**
     * 
     *
     * @author Lujinglei
     * description 添加发票重做记录
     * @date 2016年5月13日
     * @param WrCollectDTO
     * @return
     * @throws SQLException
     */
    public int addDelRecord(WrCollectDTO wrCollectDTO) throws SQLException;
    
    /**
     * 
     *
     * @author Lujinglei
     * description 查询旧发票记录
     * @date 2016年5月16日
     * @return
     * @throws SQLException
     */
    public List<WrCollectDTO> getOldInvoiceRecord(String invoiceNo, String sapCode) throws SQLException;
    
    /**
     * 
     * @author wangfl
     * 批量新增WrCollect
     * @date 2016年5月13日
     * @param wrCollectList
     * @return
     */
    int insertList(@Param("wrCollectList") List<WrCollect> wrCollectList);
    
    /**
     * 
     * @author wangfl
     * 分页查询代开发票编辑列表
     * @date 2016年5月16日
     * @param dto
     * @return
     */
    List<StayInvoiceDTO> findStayInvoicePageList(StayInvoiceDTO dto);
    
    /**
     * 
     * @author wangfl
     * 查询总的代开发票编辑数量
     * @date 2016年5月16日
     * @return
     */
    int findStayInvoiceTotalNum(StayInvoiceDTO dto);
    
    /**
     * 
     * @author wangfl
     * 更新代开发票信息
     * @date 2016年5月16日
     * @param wrCollectDTO
     * @return
     */
    int updateWrCollectByTsId(WrCollectDTO wrCollectDTO);
}