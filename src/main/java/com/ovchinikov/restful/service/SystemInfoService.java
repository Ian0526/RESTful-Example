package com.ovchinikov.restful.service;

import com.ovchinikov.restful.model.SystemInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class SystemInfoService {

    private final SystemInfo systemInfo;

    public SystemInfoService() {
        systemInfo = new SystemInfo();
    }

    @GetMapping("/memory")
    public SystemMemoryInfo getMemoryInfo() {
        int totalMemory = (int) systemInfo.fetchMaxRamMemory();
        int usedMemory = systemInfo.fetchCurrentMemoryUsage();
        int freeMemory = totalMemory - usedMemory;

        return new SystemMemoryInfo(totalMemory, usedMemory, freeMemory);
    }

    @GetMapping("/cpu")
    public SystemCpuInfo getCpuInfo() {
        String cpuInfo = systemInfo.fetchCpuInfo();
        return new SystemCpuInfo(cpuInfo);
    }

    @GetMapping("/load")
    public String getLoadInfo() {
        return "Load Info: Placeholder value";
    }

    // Define classes to represent JSON data
    private static class SystemMemoryInfo {
        private int totalMemory;
        private int usedMemory;
        private int freeMemory;

        public SystemMemoryInfo(int totalMemory, int usedMemory, int freeMemory) {
            this.totalMemory = totalMemory;
            this.usedMemory = usedMemory;
            this.freeMemory = freeMemory;
        }

        // Getters and setters
        public int getTotalMemory() {
            return totalMemory;
        }

        public void setTotalMemory(int totalMemory) {
            this.totalMemory = totalMemory;
        }

        public int getUsedMemory() {
            return usedMemory;
        }

        public void setUsedMemory(int usedMemory) {
            this.usedMemory = usedMemory;
        }

        public int getFreeMemory() {
            return freeMemory;
        }

        public void setFreeMemory(int freeMemory) {
            this.freeMemory = freeMemory;
        }
    }

    private static class SystemCpuInfo {
        private String cpuInfo;

        public SystemCpuInfo(String cpuInfo) {
            this.cpuInfo = cpuInfo;
        }

        // Getters and setters
        public String getCpuInfo() {
            return cpuInfo;
        }

        public void setCpuInfo(String cpuInfo) {
            this.cpuInfo = cpuInfo;
        }
    }
}