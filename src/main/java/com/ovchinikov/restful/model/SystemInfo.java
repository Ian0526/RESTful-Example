package com.ovchinikov.restful.model;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class SystemInfo {

    private final String os;
    private final String cpu;
    private final String ram;

    private final long maxRamMemory;
    private final long maxDiskSpace;

    private final Sigar sigar;

    public SystemInfo() {
        sigar = new Sigar();
        os = System.getProperty("os.name");
        cpu = System.getenv("PROCESSOR_IDENTIFIER");
        Mem mem = null;
        try {
            mem = sigar.getMem();
        } catch (SigarException e) {
            e.printStackTrace();
        }
        ram = mem != null ? String.valueOf(mem.getTotal() / (1024 * 1024)) : "N/A";
        maxRamMemory = mem != null ? mem.getTotal() / (1024 * 1024) : 0;
        maxDiskSpace = 0; // You need to implement fetching disk space information
    }

    /**
     * Fetches the operating system name.
     *
     * @return The operating system name.
     */
    public String fetchOperatingSystem() {
        return os;
    }

    /**
     * Fetches the CPU information.
     *
     * @return The CPU information.
     */
    public String fetchCpuInfo() {
        return cpu;
    }

    /**
     * Fetches the total RAM memory in MB.
     *
     * @return The total RAM memory in MB.
     */
    public String fetchTotalRamMemory() {
        return ram;
    }

    /**
     * Fetches the maximum RAM memory in MB.
     *
     * @return The maximum RAM memory in MB.
     */
    public long fetchMaxRamMemory() {
        return maxRamMemory;
    }

    /**
     * Fetches the maximum disk space in MB.
     *
     * @return The maximum disk space in MB.
     */
    public long fetchMaxDiskSpace() {
        return maxDiskSpace;
    }

    /**
     * This method retrieves the current memory usage in MB.
     *
     * @return The rounded integer value of the amount of MB used currently.
     */
    public int fetchCurrentMemoryUsage() {
        try {
            Mem mem = sigar.getMem();
            return (int) (mem.getUsed() / (1024 * 1024));
        } catch (SigarException e) {
            e.printStackTrace();
            return -1; // Return a negative value to indicate error
        }
    }

    /**
     * This method retrieves the current CPU usage percentage.
     *
     * @return The rounded integer value of the CPU usage percentage.
     */
    public int fetchCurrentCpuUsage() {
        try {
            double cpuUsage = sigar.getCpuPerc().getCombined() * 100;
            return (int) Math.round(cpuUsage);
        } catch (SigarException e) {
            e.printStackTrace();
            return -1; // Return a negative value to indicate error
        }
    }
}