/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.security.pages.secure;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.security.actions.WaspAction;
import org.apache.wicket.security.checks.ClassSecurityCheck;
import org.apache.wicket.security.checks.ISecurityCheck;

/**
 * Shows inherited instantiation checks.
 * 
 * @author marrink
 * 
 */
public class PageC2 extends PageC
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiation check that requires foo rights instead of the default access rights.
	 */
	static final ISecurityCheck alternate2 = new ClassSecurityCheck(PageC2.class)
	{

		/**
		 *  
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * @see org.apache.wicket.security.checks.ClassSecurityCheck#isActionAuthorized(org.apache.wicket.security.actions.WaspAction)
		 */
		@Override
		public boolean isActionAuthorized(WaspAction action)
		{
			if (isAuthenticated())
				return getStrategy().isClassAuthorized(getClazz(),
					action.add(getActionFactory().getAction("foo")));
			throw new RestartResponseAtInterceptPageException(getLoginPage());
		}

	};

	/**
	 * 
	 */
	public PageC2()
	{
		super();
	}

}
