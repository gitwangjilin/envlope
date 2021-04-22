package com.redshield.envlope.factory;


import com.redshield.envlope.constant.TemplateType;
import com.redshield.envlope.entity.EnterpriseInfo;
import com.redshield.envlope.entity.license.RegLicense;
import com.redshield.envlope.entity.license.RegTypeALicense;

/*******************************************************************************
 * - Copyright (c)  2018  gla.com
 *   @author Seven Liu
 * - File Name: ParseLicenseFactory
 * - @Author: Seven Liu
 * - Description:
 *
 *
 * - History:
 * Date         Author          Modification
 * 2019-03-29    Seven Liu    Create the current class
 *******************************************************************************/
public class ParseLicenseFactory {

    public static RegLicense parse(String entity, EnterpriseInfo enterpriseInfo) {
        String type = enterpriseInfo.getTemplateType();
        if (type.substring(0,1).indexOf(TemplateType.LICENSE_A) != -1) {
            return new RegTypeALicense(enterpriseInfo,entity);
        }
//		} else if (type.indexOf(TemplateType.LICENSE_B) != -1) {
//			return new BizTypeBLicense(entity);
//		} else if (type.indexOf(TemplateType.LICENSE_C) != -1) {
//			return new BizTypeCLicense(entity);
//		} else if (type.indexOf(TemplateType.LICENSE_D) != -1) {
//			return new BizTypeDLicense(entity);
//		} else if (type.indexOf(TemplateType.LICENSE_E) != -1) {
//			return new BizTypeELicense(entity);
//		} else if (type.indexOf(TemplateType.LICENSE_F) != -1) {
//			return new BizTypeFLicense(entity);
//		} else if (type.indexOf(TemplateType.LICENSE_G) != -1) {
//			return new BizTypeGLicense(entity);
//		} else if (type.indexOf(TemplateType.LICENSE_H) != -1) {
//			return new BizTypeHLicense(entity);
//		}else if (type.indexOf(TemplateType.LICENSE_I) != -1) {
//			return new BizTypeILicense(entity);
//		}
        return null;
    }

}
