package com.corp.concepts.datagrid.controller;

import com.corp.concepts.datagrid.model.CommandResponse;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/api/data")
public class DataController {

    private HazelcastInstance hazelcastInstance;

    public DataController(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    private ConcurrentMap<String, String> retrieveMap() {
        return hazelcastInstance.getMap("dataMap");
    }

    @PostMapping("/put")
    public CommandResponse put(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
        retrieveMap().put(key, value);
        return new CommandResponse(value);
    }

    @GetMapping("/get")
    public CommandResponse get(@RequestParam(value = "key") String key) {
        String value = retrieveMap().get(key);
        return new CommandResponse(value);
    }

}
