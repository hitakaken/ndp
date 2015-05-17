package com.novbank.ndp.kernel.service;

import com.novbank.ndp.kernel.exception.Problems;

import javax.jcr.Session;
import java.io.File;

/**
 * Created by CaoKe on 2015/5/16.
 */
public interface RepositoryService {
    /**
     * Calculate the total size of all the binary properties in the repository
     *
     * @return size in bytes
     */
    Long getRepositorySize();

    /**
     * Calculate the number of objects in the repository
     *
     * @return number of objects in the repository
     */
    Long getRepositoryObjectCount();

    /**
     * This method backups up a running repository
     *
     * @param session the session
     * @param backupDirectory the backup directory
     * @return problems
     */
    Problems backupRepository(Session session, File backupDirectory);

    /**
     * This methods restores the repository from a backup
     *
     * @param session the session
     * @param backupDirectory the backup directory
     * @return problems
     */
    Problems restoreRepository(Session session, File backupDirectory);
}
