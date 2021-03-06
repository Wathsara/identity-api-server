/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *                                                                         
 * Licensed under the Apache License, Version 2.0 (the "License");         
 * you may not use this file except in compliance with the License.        
 * You may obtain a copy of the License at                                 
 *                                                                         
 * http://www.apache.org/licenses/LICENSE-2.0                              
 *                                                                         
 * Unless required by applicable law or agreed to in writing, software     
 * distributed under the License is distributed on an "AS IS" BASIS,       
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and     
 * limitations under the License.
 */

package org.wso2.carbon.identity.api.server.authenticators.common.factory;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.application.mgt.ApplicationManagementService;

/**
 * Factory Beans serves as a factory for creating other beans within the IOC container. This factory bean is used to
 * instantiate the ApplicationManagementService type of object inside the container.
 */
public class ApplicationMgtOSGIServiceFactory extends AbstractFactoryBean<ApplicationManagementService> {

    private ApplicationManagementService applicationManagementService;

    @Override
    public Class<?> getObjectType() {

        return Object.class;
    }

    @Override
    protected ApplicationManagementService createInstance() throws Exception {

        if (this.applicationManagementService == null) {
            ApplicationManagementService taskOperationService = (ApplicationManagementService) PrivilegedCarbonContext.
                    getThreadLocalCarbonContext().getOSGiService(ApplicationManagementService.class, null);
            if (taskOperationService != null) {
                this.applicationManagementService = taskOperationService;
            } else {
                throw new Exception("Unable to retrieve applicationManagementService service.");
            }
        }
        return this.applicationManagementService;
    }
}
