DESCRIPTION = "WFD : Wifi Display (Eurogiciel's Miracast stack)"
SUMMARY = "WFD Sink - Wifi Display"
HOMEPAGE = "http://www.eurogiciel.fr/fr/produits/multimedia-open-source/oss-miracast"
SECTION = "multimedia"

LICENSE = "EUROGICIEL"
LIC_FILES_CHKSUM = "file://COPYING;md5=0fe60d4e986b1d6d2e540aefa16a8f6e"

FILES_${PN} = "${sysconfdir} ${bindir} ${datadir} ${libdir} "

# -- Build dependencies
DEPENDS = "openssl"

# -- Runtime dependencies
RDEPENDS_${PN} = " \
                  wpa-supplicant (>=2.2) tcpdump \
                  gstreamer1.0 \
                  gstreamer1.0-plugins-base-audioconvert gstreamer1.0-plugins-base-alsa \
                  gstreamer1.0-plugins-base-volume gstreamer1.0-plugins-base-typefindfunctions \
                  gstreamer1.0-plugins-good-udp gstreamer1.0-plugins-good-audioparsers \
                  gstreamer1.0-plugins-bad-mpegtsdemux gstreamer1.0-plugins-bad-videoparsersbad \
                  imx-gst1.0-plugin \
"

WFD_MANAGER_CONF_imx6qsabreauto = "wfd-sink_imx6sabreauto_conf"
WFD_MANAGER_CONF_imx6qsabresd = "wfd-sink_imx6sabresd_conf"
WFD_MANAGER_CONF_imx6dlsabreauto = "wfd-sink_imx6sabreauto_conf"
WFD_MANAGER_CONF_imx6dlsabresd = "wfd-sink_imx6sabresd_conf"

SRC_URI += " file://${WFD_MANAGER_CONF} \
"

SERVICE="wfd"
START_NUMBER="62"

# Uninstall files from target
do_uninstall () {
    # Remove wfd binaries
    rm -f ${D}${bindir}/${PN}
    rm -f ${D}${bindir}/remote_i2c
    rm -f ${D}${bindir}/send_wfd_message
    rm -f ${D}${bindir}/uibc
    rm -f ${D}${bindir}/hdcp
    rm -f ${D}${bindir}/rtsp_sink
    rm -f ${D}${bindir}/wfd_demux

    # Remove wfd configs
    rm -f ${D}${sysconfdir}/${PN}.conf
    rm -f ${D}${sysconfdir}/wfd_manager.conf
    rm -rf ${D}${sysconfdir}/${WFD_MANAGER_CONF}

    # Remove wfd init service
    rm -f ${D}${sysconfdir}/rc5.d/S${START_NUMBER}${SERVICE}

    # Remove wfd scripts
    rm -f ${D}${bindir}/certification.sh
    rm -f ${D}${bindir}/certif_player.sh
    rm -f ${D}${bindir}/demux.sh
    rm -f ${D}${bindir}/hdcp.sh
    rm -f ${D}${bindir}/i2c.sh
    rm -f ${D}${bindir}/p2p_connect.sh
    rm -f ${D}${bindir}/player.sh
    rm -f ${D}${bindir}/rtsp.sh
    rm -f ${D}${bindir}/uibc.sh
    rm -f ${D}${bindir}/wfd_manager.sh
    rm -f ${D}${sysconfdir}/init.d/wfd.sh

    # Remove wfd libraries and links
    rm -f ${D}${libdir}/libconfig.so.0.0.0
    rm -f ${D}${libdir}/libwpactrl.so.0.0.0
    rm -f ${D}${libdir}/libconfig.so.0
    rm -f ${D}${libdir}/libwpactrl.so.0

    # Remove wfd sink shared ressources
    rm -rf ${D}${datadir}/wfd-sink
}
