package com.dc.esb.servicegov.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

import com.dc.esb.servicegov.dao.impl.CategoryWordDAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.MetadataHis;
import com.dc.esb.servicegov.service.VersionService;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.util.Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dc.esb.servicegov.dao.impl.MetadataDAOImpl;
import com.dc.esb.servicegov.entity.CategoryWord;
import com.dc.esb.servicegov.entity.Metadata;
import com.dc.esb.servicegov.util.DateUtils;

@Service
@Transactional
public class MetadataServiceImpl extends AbstractBaseService<Metadata,String>{
    private static final Log log = LogFactory.getLog(MetadataServiceImpl.class);
    @Autowired
    private MetadataDAOImpl metadataDAOImpl;
    @Autowired
    private CategoryWordDAOImpl categoryWordDAO;
    @Autowired
    private OperationServiceImpl operationServiceImpl;
    @Autowired
    private VersionServiceImpl versionService;
    @Autowired
    private MetadataHisServiceImpl metadataHisService;
    public List<Metadata> getAllMetadata() {
        String hql = " from " + Metadata.class.getName() + " order by METADATA_ID asc";
    	List<Metadata> list = metadataDAOImpl.find(hql);
        return list;
    }

    public List<Metadata> getByMetadataId(String metadataId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("metadataId", metadataId);
        return metadataDAOImpl.findBy(params);
    }

    public List<Metadata> getByMetadataName(String metadataName) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("metadataName", metadataName);
        return metadataDAOImpl.findBy(params);
    }

    public List<Metadata> getByChineseName(String chineseName) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("chineseName", chineseName);
        return metadataDAOImpl.findBy(params);
    }

    public List<Metadata> getByCategoryWordId(String categoryWordId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("categoryWordId", categoryWordId);
        return metadataDAOImpl.findBy(params);
    }

    public List<Metadata> getByRemark(String remark) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("remark", remark);
        return metadataDAOImpl.findBy(params);
    }

    public List<Metadata> getByType(String type) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", type);
        return metadataDAOImpl.findBy(params);
    }

    public List<Metadata> getByLength(String length) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("length", length);
        return metadataDAOImpl.findBy(params);
    }

    public List<Metadata> getByScale(String scale) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("scale", scale);
        return metadataDAOImpl.findBy(params);
    }

    public List<Metadata> getByEnumId(String enumId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("enumId", enumId);
        return metadataDAOImpl.findBy(params);
    }

    public List<Metadata> getByMetadataAlias(String metadataAlias) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("metadataAlias", metadataAlias);
        return metadataDAOImpl.findBy(params);

    }

    public List<Metadata> getByBussDefine(String bussDefine) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("bussDefine", bussDefine);
        return metadataDAOImpl.findBy(params);
    }

    public List<Metadata> getByBussRule(String bussRule) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("bussRule", bussRule);
        return metadataDAOImpl.findBy(params);
    }

    public List<Metadata> getByDataSource(String dataSource) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("dataSource", dataSource);
        return metadataDAOImpl.findBy(params);
    }

    public List<Metadata> getByTemplateId(String templateId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("templateId", templateId);
        return metadataDAOImpl.findBy(params);
    }

    public List<Metadata> getByStatus(String status) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("status", status);
        return metadataDAOImpl.findBy(params);
    }

    public List<Metadata> getByVersion(String version) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("version", version);
        return metadataDAOImpl.findBy(params);
    }

    public List<Metadata> getByPotUser(String potUser) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("potUser", potUser);
        return metadataDAOImpl.findBy(params);
    }

    public List<Metadata> getByPotDate(String potDate) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("potDate", potDate);
        return metadataDAOImpl.findBy(params);
    }

    public List<Metadata> getByAuditUser(String auditUser) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("auditUser", auditUser);
        return metadataDAOImpl.findBy(params);
    }

    public List<Metadata> getByAuditDate(String auditDate) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("auditDate", auditDate);
        return metadataDAOImpl.findBy(params);
    }

    public boolean addMetadata(Metadata metadata) {
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        if(StringUtils.isEmpty(metadata.getCategoryWordId())){
            metadata.setCategoryWordId(null);
        }
        metadata.setOptUser(userName);
		metadata.setOptDate(DateUtils.format(new Date()));
        metadataDAOImpl.save(metadata);
        String versionId = versionService.addVersion(Constants.Version.TARGET_TYPE_METADATA, metadata.getMetadataId(), Constants.Version.TYPE_ELSE);
        metadata.setVersionId(versionId);
        metadataDAOImpl.save(metadata);
        return true;
    }

    public boolean modifyMetadata(Metadata metadata) {
        metadataDAOImpl.save(metadata);
        versionService.editVersion(metadata.getVersionId());
        if(Constants.Metadata.STATUS_OUTDATED.equals(metadata.getStatus())){//过时
            MetadataHis metadataHis = new MetadataHis(metadata);
            metadataHisService.addMetadataHis(metadataHis);
        }
        return true;
    }

    public void deleteMetadata(String metadataId) {
        metadataDAOImpl.delete(metadataId);
    }
    
    public boolean deleteMetadatas(String metadataIds){
    	String[] ids = metadataIds.split("\\,");
    	if(ids != null && ids.length > 0){
    		for(String metadataId : ids){
                //关联场景不予删除
                if(operationServiceImpl.judgeByMetadataId(metadataId)){
                    return false;
                }
    			deleteMetadata(metadataId);
    		}
    	}
        return true;
    }
    public String genderHql(Map<String, String[]> values){
        String hql = "";
        if(values != null && values.size() > 0){
            for(String key:values.keySet()){
                if(key.equals("metadataName") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        hql += " and upper(a.metadataName) like upper('%" + values.get(key)[0] + "%') ";
                    }
                }
                if(key.equals("chineseName") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        try {
                            hql += " and a.chineseName like '%" + URLDecoder.decode(values.get(key)[0], "utf-8") + "%' ";
                        }catch (UnsupportedEncodingException e) {
                            log.error(e,e);
                            log.error("中文名称转码错误！");
                        }
                    }
                }
                if(key.equals("metadataId") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        hql += " and upper(a.metadataId) like upper('%" + values.get(key)[0] + "%') ";
                    }
                }
                if(key.equals("metadataAlias") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        hql += " and a.metadataAlias like '%" + values.get(key)[0] + "%' ";
                    }
                }
                if(key.equals("status") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        hql += " and a.status like '%" + values.get(key)[0] + "%' ";
                    }
                }
                if(key.equals("version") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        hql += " and a.version like '%" + values.get(key)[0] + "%' ";
                    }
                }
                if(key.equals("optUser") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        hql += " and a.optUser like '%" + values.get(key)[0] + "%' ";
                    }
                }
                if(key.equals("startDate") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        hql += " and a.optDate >'" + values.get(key)[0] + "' ";
                    }
                }
                if(key.equals("endDate") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        hql += " and a.optDate <'" + values.get(key)[0] + " 23:59:59' ";
                    }
                }

                if(key.equals("categoryWordId") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        hql += " and a.categoryWordId ='" + values.get(key)[0] + "' ";
                    }
                }
            }
        }
        return hql;
    }

    public String genderSql(Map<String, String[]> values){
        String sql = "";
        if(values != null && values.size() > 0){
            for(String key:values.keySet()){
                if(key.equals("metadataName") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        sql += " and a.metadata_name like '%" + values.get(key)[0] + "%' ";
                    }
                }
                if(key.equals("chineseName") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        try {
                            sql += " and a.chinese_name like '%" + URLDecoder.decode(values.get(key)[0], "utf-8") + "%' ";
                        }catch (UnsupportedEncodingException e) {
                            log.error(e,e);
                            log.error("中文名称转码错误！");
                        }
                    }
                }
                if(key.equals("metadataId") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        sql += " and a.metadata_id like '%" + values.get(key)[0] + "%' ";
                    }
                }
                if(key.equals("metadataAlias") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        sql += " and a.metadata_alias like '%" + values.get(key)[0] + "%' ";
                    }
                }
                if(key.equals("status") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        sql += " and a.status like '%" + values.get(key)[0] + "%' ";
                    }
                }
                if(key.equals("version") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        sql += " and a.version like '%" + values.get(key)[0] + "%' ";
                    }
                }
                if(key.equals("startDate") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        sql += " and a.opt_date >'" + values.get(key)[0] + "' ";
                    }
                }
                if(key.equals("endDate") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        sql += " and a.opt_date <'" + values.get(key)[0] + " 23:59:59' ";
                    }
                }

                if(key.equals("categoryWordId") && values.get(key) != null && values.get(key).length > 0 ){
                    if(StringUtils.isNotEmpty(values.get(key)[0])){
                        sql += " and a.category_word_id ='" + values.get(key)[0] + "' ";
                    }
                }
            }
        }
        return sql;
    }

    public long queryCount(Map<String, String[]> values){
        String hql = "select count(*) from Metadata a where 1=1 ";
        hql += genderHql(values);
        return (Long)metadataDAOImpl.findUnique(hql);
    }
    public List<Metadata> queryByCondition(Map<String, String[]> values, Page page){
    	String hql = " from Metadata a where 1=1 ";
        hql += genderHql(values);
    	return metadataDAOImpl.findBy(hql, page, new ArrayList<SearchCondition>());
    }
    //关联categoryWord表，显示chineseWord
    public List<MetadataBean> queryByCondition2(Map<String, String[]> values, Page page){
//        String hql = "select a,b.chineseWord from Metadata a, CategoryWord b where 1=1 and a.categoryWordId=b.englishWord ";
        String hql = "from Metadata a left join a.categoryWord where 1=1 ";
        hql += genderHql(values);
        hql += " order by a.categoryWordId";
        List<Object[]> list = metadataDAOImpl.findBy(hql, page, new ArrayList<SearchCondition>());
        List<MetadataBean> metadataBeanList = new ArrayList<MetadataBean>();
        for (Object[] per : list){
            if(per[1] != null){
                CategoryWord cw = (CategoryWord)per[1];
                metadataBeanList.add(new MetadataBean((Metadata)per[0], cw.getChineseWord()));
            }else{
                metadataBeanList.add(new MetadataBean((Metadata)per[0], ""));
            }
        }
        return metadataBeanList;
    }

    public static class MetadataBean{
        private String metadataId;
        private String metadataName;
        private String chineseName;
        private String categoryWordId;
        private String remark;
        private String type;
        private String length;
        private String scale;
        private String enumId;
        private String metadataAlias;
        private String bussDefine;
        private String bussRule;
        private String dataSource;
        private String templateId;
        private String status;
        private String version;
        private String optUser;
        private String optDate;
        private String auditUser;
        private String auditDate;
        private String processId;
        private String dataFormula;
        private String buzzCategory;
        private String dataCategory;
        //类别词中文
        private String categoryChineseWord;

        public MetadataBean(Metadata md,String categoryChineseWord){
            setMetadataId(md.getMetadataId());
            setMetadataName(md.getMetadataName());
            setChineseName(md.getChineseName());
            setCategoryWordId(md.getCategoryWordId());
            setRemark(md.getRemark());
            setType(md.getType());
            setLength(md.getLength());
            setScale(md.getScale());
            setEnumId(md.getEnumId());
            setMetadataAlias(md.getMetadataAlias());
            setBussDefine(md.getBussDefine());
            setBussRule(md.getBussRule());
            setDataSource(md.getDataSource());
            setTemplateId(md.getTemplateId());
            setStatus(md.getStatus());
            //TZB没有version
            if(null != md.getVersion())
                setVersion(md.getVersion().getCode());
            setOptUser(md.getOptUser());
            setOptDate(md.getOptDate());
            setAuditUser(md.getAuditUser());
            setAuditDate(md.getAuditDate());
            setProcessId(md.getProcessId());
            setDataFormula(md.getDataFormula());
            setBuzzCategory(md.getBuzzCategory());
            setDataCategory(md.getDataCategory());
            setCategoryChineseWord(categoryChineseWord);
        }

        public String getMetadataId() {
            return metadataId;
        }

        public void setMetadataId(String metadataId) {
            this.metadataId = metadataId;
        }

        public String getMetadataName() {
            return metadataName;
        }

        public void setMetadataName(String metadataName) {
            this.metadataName = metadataName;
        }

        public String getChineseName() {
            return chineseName;
        }

        public void setChineseName(String chineseName) {
            this.chineseName = chineseName;
        }

        public String getCategoryWordId() {
            return categoryWordId;
        }

        public void setCategoryWordId(String categoryWordId) {
            this.categoryWordId = categoryWordId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getScale() {
            return scale;
        }

        public void setScale(String scale) {
            this.scale = scale;
        }

        public String getEnumId() {
            return enumId;
        }

        public void setEnumId(String enumId) {
            this.enumId = enumId;
        }

        public String getMetadataAlias() {
            return metadataAlias;
        }

        public void setMetadataAlias(String metadataAlias) {
            this.metadataAlias = metadataAlias;
        }

        public String getBussDefine() {
            return bussDefine;
        }

        public void setBussDefine(String bussDefine) {
            this.bussDefine = bussDefine;
        }

        public String getBussRule() {
            return bussRule;
        }

        public void setBussRule(String bussRule) {
            this.bussRule = bussRule;
        }

        public String getDataSource() {
            return dataSource;
        }

        public void setDataSource(String dataSource) {
            this.dataSource = dataSource;
        }

        public String getTemplateId() {
            return templateId;
        }

        public void setTemplateId(String templateId) {
            this.templateId = templateId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getOptUser() {
            return optUser;
        }

        public void setOptUser(String optUser) {
            this.optUser = optUser;
        }

        public String getOptDate() {
            return optDate;
        }

        public void setOptDate(String optDate) {
            this.optDate = optDate;
        }

        public String getAuditUser() {
            return auditUser;
        }

        public void setAuditUser(String auditUser) {
            this.auditUser = auditUser;
        }

        public String getAuditDate() {
            return auditDate;
        }

        public void setAuditDate(String auditDate) {
            this.auditDate = auditDate;
        }

        public String getProcessId() {
            return processId;
        }

        public void setProcessId(String processId) {
            this.processId = processId;
        }

        public String getDataFormula() {
            return dataFormula;
        }

        public void setDataFormula(String dataFormula) {
            this.dataFormula = dataFormula;
        }

        public String getBuzzCategory() {
            return buzzCategory;
        }

        public void setBuzzCategory(String buzzCategory) {
            this.buzzCategory = buzzCategory;
        }

        public String getDataCategory() {
            return dataCategory;
        }

        public void setDataCategory(String dataCategory) {
            this.dataCategory = dataCategory;
        }

        public String getCategoryChineseWord() {
            return categoryChineseWord;
        }

        public void setCategoryChineseWord(String categoryChineseWord) {
            this.categoryChineseWord = categoryChineseWord;
        }
    }
    
    public boolean uniqueValid(String metadataId){
        Map<String, String > params = new HashMap<String, String>();
        params.put("metadataId", metadataId);
        params.put("status", Constants.Metadata.STATUS_FORMAL);
    	List<Metadata> list = metadataDAOImpl.findBy(params);
    	if(list != null && list.size() > 0){
    		return false;
    	}
    	return true;
    }

    public boolean uniqueChineseNameValid(String chineseName){
        Map<String, String > params = new HashMap<String, String>();
        params.put("chineseName", chineseName);
        params.put("status", Constants.Metadata.STATUS_FORMAL);
        List<Metadata> list = metadataDAOImpl.findBy(params);
        if(list != null && list.size() > 0){
            return false;
        }
        return true;
    }

    public List<CategoryWord> categoryWord(){
    	return categoryWordDAO.getAll();
    }

    @Override
    public HibernateDAO<Metadata, String> getDAO() {
        return metadataDAOImpl;
    }
}
