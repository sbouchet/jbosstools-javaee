/******************************************************************************* 
 * Copyright (c) 2012 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/
package org.jboss.tools.cdi.deltaspike.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IPath;
import org.jboss.tools.cdi.internal.core.impl.definition.AbstractMemberDefinition;
import org.jboss.tools.cdi.internal.core.impl.definition.AnnotationDefinition;

/**
 * @author Viacheslav Kabanovich
 */
public class DeltaspikeSecurityBindingConfiguration {
	String securityBindingTypeName;
	AnnotationDefinition securityBindingType;
	
	Map<AbstractMemberDefinition, SecurityBindingDeclaration> boundMembers = new HashMap<AbstractMemberDefinition, SecurityBindingDeclaration>();
	Set<DeltaspikeAuthorityMethod> authorizerMembers = new HashSet<DeltaspikeAuthorityMethod>();

	Set<IPath> involvedResources = new HashSet<IPath>();

	public DeltaspikeSecurityBindingConfiguration(String securityBindingTypeName) {
		this.securityBindingTypeName = securityBindingTypeName;
	}

	public void setSecurityBundingTypeDefinition(AnnotationDefinition securityBindingType, DeltaspikeSecurityDefinitionContext context) {
		this.securityBindingType = securityBindingType;
	}

	public void clear(IPath path) {
		involvedResources.remove(path);
	}

	public void clear(String typeName) {
		Iterator<AbstractMemberDefinition> it = boundMembers.keySet().iterator();
		while(it.hasNext()) {
			if(typeName.equals(it.next().getTypeDefinition().getQualifiedName())) {
				it.remove();
			}
		}
		Iterator<DeltaspikeAuthorityMethod> it2 = authorizerMembers.iterator();
		while(it2.hasNext()) {
			if(typeName.equals(it2.next().getDeclaringTypeName())) {
				it2.remove();
			}
		}
	}

	public Map<AbstractMemberDefinition, SecurityBindingDeclaration> getBoundMembers() {
		return boundMembers;
	}

	public Set<DeltaspikeAuthorityMethod> getAuthorizerMembers() {
		return authorizerMembers;
	}

	public String getSecurityBindingTypeName() {
		return securityBindingTypeName;
	}

	public AnnotationDefinition getSecurityBindingTypeDefinition() {
		return securityBindingType;
	}

	public Set<IPath> getInvolvedTypes() {
		return involvedResources;
	}

}
