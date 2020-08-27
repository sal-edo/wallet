/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.saledo.wallet.config;

import java.util.Date;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

/**
 * Override of Spring DefaultErrorAttributes component which is returned in case of
 * error response. Timestamp is converted to Instant and cause of error is set inside
 * "cause" field instead of "message" so that error response is same in all cases and it
 * follows ExceptionResponseDto format.
 */
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest,
			ErrorAttributeOptions errorAttributeOptions) {
		Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest,
				errorAttributeOptions);
		errorAttributes.put("timestamp",
				((Date) errorAttributes.get("timestamp")).toInstant());
		errorAttributes.put("cause", errorAttributes.get("message"));
		errorAttributes.remove("message");
		errorAttributes.put("status", String.valueOf(errorAttributes.get("status")));
		return errorAttributes;
	}
}
