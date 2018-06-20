include wfd-sink.inc

SRC_URI += "http://localhost/bin-wfd-sink_${PV}.tar.gz"

SRC_URI[md5sum] = "df79c40c3b46f084050687b06a7208d0"
SRC_URI[sha256sum] = "697b583b20d3db0d1b0f0e13708cc22deaca5c3dc2cb080160ae7e85ca351af6"

S = "${WORKDIR}"

DEPENDS += "qtbase"

INHIBIT_PACKAGE_STRIP = "1"

do_fetch() {
    echo "using local file";
}

do_install() {
    install -d ${D}/etc
    install -d ${D}/etc/init.d
    install -d ${D}/usr/share
    install -d ${D}/usr/bin
    install -d ${D}/usr/lib
    install -d ${D}/etc/rc5.d
    install -d ${D}${sysconfdir}/${WFD_MANAGER_CONF}

    # Install configuration files
    install -c -m 755 ${WORKDIR}/${WFD_MANAGER_CONF}/conf_LVDS_HDMI/wfd_sink.conf ${D}${sysconfdir}/wfd_sink.conf
    install -c -m 755 ${WORKDIR}/${WFD_MANAGER_CONF}/conf_LVDS_HDMI/wfd_manager.conf ${D}${sysconfdir}/wfd_manager.conf
    cp -r ${WORKDIR}/${WFD_MANAGER_CONF} ${D}${sysconfdir}

    # Install WFD startup scripts
    install -c -m 755 ${S}/etc/init.d/wfd.sh ${D}/etc/init.d/wfd.sh
    cd ${D}/etc/rc5.d && ln -sf ../init.d/${SERVICE}.sh S${START_NUMBER}${SERVICE}

    # Install wfd scripts
    install -c -m 755 ${S}/usr/bin/certification.sh ${D}/usr/bin/certification.sh
    install -c -m 755 ${S}/usr/bin/certif_player.sh ${D}/usr/bin/certif_player.sh
    install -c -m 755 ${S}/usr/bin/demux.sh ${D}/usr/bin/demux.sh
    install -c -m 755 ${S}/usr/bin/hdcp.sh ${D}/usr/bin/hdcp.sh
    install -c -m 755 ${S}/usr/bin/i2c.sh ${D}/usr/bin/i2c.sh
    install -c -m 755 ${S}/usr/bin/p2p_connect.sh ${D}/usr/bin/p2p_connect.sh
    install -c -m 755 ${S}/usr/bin/player.sh ${D}/usr/bin/player.sh
    install -c -m 755 ${S}/usr/bin/rtsp.sh ${D}/usr/bin/rtsp.sh
    install -c -m 755 ${S}/usr/bin/uibc.sh ${D}/usr/bin/uibc.sh
    install -c -m 755 ${S}/usr/bin/wfd_manager.sh ${D}/usr/bin/wfd_manager.sh

    # Install wfd binaries
    install -c -m 755 ${S}/usr/bin/hdcp ${D}/usr/bin/hdcp
    install -c -m 755 ${S}/usr/bin/remote_i2c ${D}/usr/bin/remote_i2c
    install -c -m 755 ${S}/usr/bin/rtsp_sink ${D}/usr/bin/rtsp_sink
    install -c -m 755 ${S}/usr/bin/send_wfd_message ${D}/usr/bin/send_wfd_message
    install -c -m 755 ${S}/usr/bin/uibc ${D}/usr/bin/uibc
    install -c -m 755 ${S}/usr/bin/wfd_demux ${D}/usr/bin/wfd_demux
    install -c -m 755 ${S}/usr/bin/wfd_manager_qt ${D}/usr/bin/wfd_manager_qt
    install -c -m 755 ${S}/usr/bin/wfd_sink ${D}/usr/bin/wfd_sink

    # Install wfd shared libs
    install -c -m 755 ${S}/usr/lib/libconfig.so.0.0.0 ${D}/usr/lib/libconfig.so.0.0.0
    install -c -m 755 ${S}/usr/lib/libwpactrl.so.0.0.0 ${D}/usr/lib/libwpactrl.so.0.0.0
    cd ${D}${libdir} && ln -sf libconfig.so.0.0.0 libconfig.so.0
    cd ${D}${libdir} && ln -sf libwpactrl.so.0.0.0 libwpactrl.so.0

    # Copy WFD sink shared ressources
    cp -a ${S}/usr/share/wfd-sink ${D}/usr/share/wfd-sink
    chown -R root:root ${D}/usr/share/wfd-sink
}