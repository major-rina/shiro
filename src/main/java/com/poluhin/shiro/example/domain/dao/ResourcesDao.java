/*
 * Copyright 2016 Stormpath, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.poluhin.shiro.example.domain.dao;

import com.poluhin.shiro.example.domain.model.Resource;

import java.util.Collection;

/**
 * Example CRUD DAO interface.
 */
public interface ResourcesDao {

    Collection<Resource> resourceList();

    Resource getResource(String id);

    Resource addResource(Resource resource);

    Resource updateResource(String id, Resource resource);

    boolean deleteResource(String id);
}
