package org.jmeld.vc;

import org.jmeld.vc.bzr.BazaarVersionControl;
import org.jmeld.vc.git.GitVersionControl;
import org.jmeld.vc.hg.MercurialVersionControl;
import org.jmeld.vc.svn.SubversionVersionControl;

import java.io.*;

import java.util.*;

public class VersionControlUtil {
    static private List<VersionControlIF> versionControlList;

    public static boolean isVersionControlled(File file) {
        return getVersionControl(file) != null;
    }

    public static List<VersionControlIF> getVersionControl(File file) {
        List<VersionControlIF> list;

        list = new ArrayList<VersionControlIF>();
        for (VersionControlIF versionControl : getVersionControlList()) {
            if (!versionControl.isInstalled()) {
                continue;
            }

            if (!versionControl.isEnabled(file)) {
                continue;
            }

            list.add(versionControl);
        }

        return list;
    }

    public static List<VersionControlIF> getVersionControlList() {
        if (versionControlList == null) {
            versionControlList = new ArrayList<VersionControlIF>();
            versionControlList.add(new SubversionVersionControl());
            versionControlList.add(new MercurialVersionControl());
            versionControlList.add(new BazaarVersionControl());
            versionControlList.add(new GitVersionControl());
        }

        return versionControlList;
    }
}
