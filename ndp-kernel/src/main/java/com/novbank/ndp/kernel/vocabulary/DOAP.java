package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.mixin.RDFProperty;
import com.novbank.ndp.kernel.mixin.RDFResource;
import com.novbank.ndp.kernel.mixin.RDFVocabulary;

/**
 * Description of a Project (DOAP) vocabulary
 *
 * Created by CaoKe on 2015/5/12.
 */
public class DOAP extends RDFVocabulary{
    public static final String NAMESPACE = "http://usefulinc.com/ns/doap#";
    public static final String PREFIX = "doap";
    public static final DOAP INSTANCE = new DOAP();

    public final RDFResource Project;
    public final RDFResource Version;
    public final RDFResource Repository;
    public final RDFResource SVNRepository;
    public final RDFResource BKRepository;
    public final RDFResource CVSRepository;
    public final RDFResource ArchRepository;
    public final RDFResource BazaarBranch;
    public final RDFResource GitRepository;
    public final RDFResource HgRepository;
    public final RDFResource DarcsRepository;

    public final RDFProperty name;
    public final RDFProperty homepage;
    public final RDFProperty old_homepage;
    public final RDFProperty created;
    public final RDFProperty shortdesc;
    public final RDFProperty description;
    public final RDFProperty release;
    public final RDFProperty mailing_list;
    public final RDFProperty category;
    public final RDFProperty license;
    public final RDFProperty repository;
    public final RDFProperty anon_root;
    public final RDFProperty browse;
    public final RDFProperty module;
    public final RDFProperty location;
    public final RDFProperty download_page;
    public final RDFProperty download_mirror;
    public final RDFProperty revision;
    public final RDFProperty file_release;
    public final RDFProperty wiki;
    public final RDFProperty bug_database;
    public final RDFProperty screenshots;
    public final RDFProperty maintainer;
    public final RDFProperty developer;
    public final RDFProperty documenter;
    public final RDFProperty translator;
    public final RDFProperty tester;
    public final RDFProperty helper;
    public final RDFProperty programming_language;
    public final RDFProperty os;
    public final RDFProperty _implements;
    public final RDFProperty service_endpoint;
    public final RDFProperty language;
    public final RDFProperty vendor;
    public final RDFProperty platform;
    public final RDFProperty audience;
    public final RDFProperty blog;

    public DOAP() {
        super(NAMESPACE,PREFIX);
        Project = addClass("Project");
        Version = addClass("Version");
        Repository = addClass("Repository");
        SVNRepository = addClass("SVNRepository");
        BKRepository = addClass("BKRepository");
        CVSRepository = addClass("CVSRepository");
        ArchRepository = addClass("ArchRepository");
        BazaarBranch = addClass("BazaarBranch");
        GitRepository = addClass("GitRepository");
        HgRepository = addClass("HgRepository");
        DarcsRepository = addClass("DarcsRepository");

        name = addProperty("name");
        homepage = addProperty("homepage");
        old_homepage = addProperty("old-homepage");
        created = addProperty("created");
        shortdesc = addProperty("shortdesc");
        description = addProperty("description");
        release = addProperty("release");
        mailing_list = addProperty("mailing-list");
        category = addProperty("category");
        license = addProperty("license");
        repository = addProperty("repository");
        anon_root = addProperty("anon-root");
        browse = addProperty("browse");
        module = addProperty("module");
        location = addProperty("location");
        download_page = addProperty("download-page");
        download_mirror = addProperty("download_mirror");
        revision = addProperty("revision");
        file_release = addProperty("file-release");
        wiki = addProperty("wiki");
        bug_database = addProperty("bug-database");
        screenshots = addProperty("screenshots");
        maintainer = addProperty("maintainer");
        developer = addProperty("developer");
        documenter = addProperty("documenter");
        translator = addProperty("translator");
        tester = addProperty("tester");
        helper = addProperty("helper");
        programming_language = addProperty("programming-language");
        os = addProperty("os");
        _implements = addProperty("implements");
        service_endpoint = addProperty("service-endpoint");
        language = addProperty("language");
        vendor = addProperty("vendor");
        platform = addProperty("platform");
        audience = addProperty("audience");
        blog = addProperty("blog");
    }
    public static final RDFResource PROJECT = INSTANCE.Project;
    public static final RDFResource VERSION = INSTANCE.Version;
    public static final RDFResource REPOSITORY = INSTANCE.Repository;
    public static final RDFResource SVN_REPOSITORY = INSTANCE.SVNRepository;
    public static final RDFResource BK_REPOSITORY = INSTANCE.BKRepository;
    public static final RDFResource CVS_REPOSITORY = INSTANCE.CVSRepository;
    public static final RDFResource ARCH_REPOSITORY = INSTANCE.ArchRepository;
    public static final RDFResource BAZAAR_BRANCH = INSTANCE.BazaarBranch;
    public static final RDFResource GIT_REPOSITORY = INSTANCE.GitRepository;
    public static final RDFResource HG_REPOSITORY = INSTANCE.HgRepository;
    public static final RDFResource DARCS_REPOSITORY = INSTANCE.DarcsRepository;

    public static final RDFProperty NAME = INSTANCE.name;
    public static final RDFProperty HOMEPAGE = INSTANCE.homepage;
    public static final RDFProperty OLD_HOMEPAGE = INSTANCE.old_homepage;
    public static final RDFProperty CREATED = INSTANCE.created;
    public static final RDFProperty SHORT_DESC = INSTANCE.shortdesc;
    public static final RDFProperty DESCRIPTION = INSTANCE.description;
    public static final RDFProperty RELEASE = INSTANCE.release;
    public static final RDFProperty MAILING_LIST = INSTANCE.mailing_list;
    public static final RDFProperty CATEGORY = INSTANCE.category;
    public static final RDFProperty LICENSE = INSTANCE.license;
    public static final RDFProperty REPOSITORY$ = INSTANCE.repository;
    public static final RDFProperty ANON_ROOT = INSTANCE.anon_root;
    public static final RDFProperty BROWSE = INSTANCE.browse;
    public static final RDFProperty MODULE = INSTANCE.module;
    public static final RDFProperty LOCATION = INSTANCE.location;
    public static final RDFProperty DOWNLOAD_PAGE = INSTANCE.download_page;
    public static final RDFProperty DOWNLOAD_MIRROR = INSTANCE.download_mirror;
    public static final RDFProperty REVISION = INSTANCE.revision;
    public static final RDFProperty FILE_RELEASE = INSTANCE.file_release;
    public static final RDFProperty WIKI = INSTANCE.wiki;
    public static final RDFProperty BUG_DATABASE = INSTANCE.bug_database;
    public static final RDFProperty SCREENSHOTS = INSTANCE.screenshots;
    public static final RDFProperty MAINTAINER = INSTANCE.maintainer;
    public static final RDFProperty DEVELOPER = INSTANCE.developer;
    public static final RDFProperty DOCUMENTER = INSTANCE.documenter;
    public static final RDFProperty TRANSLATOR = INSTANCE.translator;
    public static final RDFProperty TESTER = INSTANCE.tester;
    public static final RDFProperty HELPER = INSTANCE.helper;
    public static final RDFProperty PROGRAMMING_LANGUAGE = INSTANCE.programming_language;
    public static final RDFProperty OS = INSTANCE.os;
    public static final RDFProperty IMPLEMENTS = INSTANCE._implements;
    public static final RDFProperty SERVICE_ENDPOINT = INSTANCE.service_endpoint;
    public static final RDFProperty LANGUAGE = INSTANCE.language;
    public static final RDFProperty VENDOR = INSTANCE.vendor;
    public static final RDFProperty PLATFORM = INSTANCE.platform;
    public static final RDFProperty AUDIENCE = INSTANCE.audience;
    public static final RDFProperty BLOG = INSTANCE.blog;
}
