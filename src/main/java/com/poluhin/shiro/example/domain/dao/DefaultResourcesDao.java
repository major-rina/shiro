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
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

@Service
public class DefaultResourcesDao implements ResourcesDao {

    private final static Random RANDOM = new SecureRandom();

    private final Map<String, Resource> resourceMap = Collections.synchronizedSortedMap(new TreeMap<>());

    public DefaultResourcesDao() {
        for (int i = 0; i < 50; i++) {
            addResource(randomResource());
        }
    }

    @Override
    public Collection<Resource> resourceList() {
        return Collections.unmodifiableCollection(resourceMap.values());
    }

    @Override
    public Resource getResource(String id) {
        return resourceMap.get(id);
    }

    @Override
    public Resource addResource(Resource resource) {
        if (resource.getId() == null || resource.getId().trim().isEmpty()) {
            resource.setId(generateRandomId());
        }
        resourceMap.put(resource.getId(), resource);
        return resource;
    }

    @Override
    public Resource updateResource(String id, Resource resource) {
        return addResource(resource);
    }

    @Override
    public boolean deleteResource(String id) {
        return resourceMap.remove(id) != null;
    }


    private static Resource randomResource(String id) {
        return new Resource(id, String.valueOf(RANDOM.nextInt()), String.valueOf(RANDOM.nextInt()));
    }

    private static String generateRandomId() {
        // HIGH chance of collisions, but, this is all for fun...
        return "FN-"  + String.format("%04d", RANDOM.nextInt(9999));
    }

    private static Resource randomResource() {
        return randomResource(generateRandomId());
    }
}
