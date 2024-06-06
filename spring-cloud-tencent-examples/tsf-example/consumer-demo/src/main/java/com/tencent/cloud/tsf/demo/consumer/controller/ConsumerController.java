/*
 * Tencent is pleased to support the open source community by making Spring Cloud Tencent available.
 *
 * Copyright (C) 2019 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.tencent.cloud.tsf.demo.consumer.controller;

import com.tencent.cloud.tsf.demo.consumer.proxy.ProviderDemoService;
import com.tencent.cloud.tsf.demo.consumer.proxy.ProviderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ProviderService providerService;
	@Autowired
	private ProviderDemoService providerDemoService;

	@RequestMapping(value = "/echo-rest/{str}", method = RequestMethod.GET)
	public String restProvider(@PathVariable String str) {
		return restTemplate.getForObject("http://provider-demo/echo/" + str, String.class);
	}

	@RequestMapping(value = "/echo-feign/{str}", method = RequestMethod.GET)
	public String feignProvider(@PathVariable String str) {
		return providerDemoService.echo(str);
	}

	@RequestMapping(value = "/echo-feign-url/{str}", method = RequestMethod.GET)
	public String feignUrlProvider(@PathVariable String str) {
		return providerService.echo(str);
	}
}
