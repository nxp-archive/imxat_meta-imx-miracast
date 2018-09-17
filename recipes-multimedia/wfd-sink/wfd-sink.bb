include wfd-sink.inc

PV = "2.25"

S = "${WORKDIR}/git"

inherit qmake5
DEPENDS += "qtbase"

# BitBake uses the SRC_URI variable to point to source files regardless of their location.
# Each recipe must have a SRC_URI variable that points to the source.
SRC_URI += "git://git@bitbucket.sw.nxp.com/mss/wfd_stack_miracast.git;protocol=ssh;branch=wfd_sink_integration_v2"

#The revision of the source code used to build the package
SRCREV = "badd56e6776c971758aa1e9db31449dc24eb82b4"

# Causes tarballs of the Git repositories, including the Git metadata, to be placed in the DL_DIR directory
BB_GENERATE_MIRROR_TARBALLS = "1"

SRC_URI += " file://0001-HDCP-Disable-HDCP-support-from-the-sink.patch \
             file://0002-wfd_manager-enable-automatic-screen-connection-by-de.patch \
"

SSTATE_SCAN_FILES += "apxs"

CPPFLAGS_append = " -DGITID=\\"${SRCREV}\\" -DCONFIG_FILE=\\"/etc/wfd_sink.conf\\" "

#EXTRA_OECONF = "--with-directfb=yes"

do_configure[dirs] = "${WORKDIR}/build_qt"
do_compile[dirs] = "${WORKDIR}/build_qt"

addtask build_wfd_sink after do_compile before do_install
do_build_wfd_sink () {
    autotools_do_configure
    base_do_compile
}

do_install () {
    autotools_do_install
}

# Install files on target
do_install_append () {
    install -c -m 755 ${WORKDIR}/build_qt/wfd_manager_qt ${D}/usr/bin/
    install -d ${D}${sysconfdir}/${WFD_MANAGER_CONF}
    install -c -m 755 ${WORKDIR}/${WFD_MANAGER_CONF}/conf_LVDS_HDMI/wfd_sink.conf ${D}${sysconfdir}/wfd_sink.conf
    install -c -m 755 ${WORKDIR}/${WFD_MANAGER_CONF}/conf_LVDS_HDMI/wfd_manager.conf ${D}${sysconfdir}/wfd_manager.conf
    cp -r ${WORKDIR}/${WFD_MANAGER_CONF} ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/rc5.d
    cd ${D}${sysconfdir}/rc5.d && ln -sf ../init.d/${SERVICE}.sh S${START_NUMBER}${SERVICE}
}
