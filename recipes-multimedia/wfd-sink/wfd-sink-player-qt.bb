DESCRIPTION = "WFD Sink : Wifi Display Qt UI/Player"
SUMMARY = "WFD Sink : Wifi Display Qt UI/Player"
SECTION = "multimedia"

LICENSE = "CLOSED"

FILES_${PN} = "${bindir} ${sysconfdir} ${datadir}"

PV = "2.25"

# BitBake uses the SRC_URI variable to point to source files regardless of their location.
# Each recipe must have a SRC_URI variable that points to the source.
SRC_URI += "git://git@bitbucket.sw.nxp.com/mss/wfd_sink_player_qt.git;protocol=ssh;branch=master"

# The revision of the source code used to build the package
SRCREV = "c87d09e00d87010041cb282531c232fd863366f9"

WFD_CONF_DIR_imx6qsabreauto = "wfd-sink_imx6sabreauto_conf"
WFD_CONF_DIR_imx6qsabresd = "wfd-sink_imx6sabresd_conf"
WFD_CONF_DIR_imx6dlsabreauto = "wfd-sink_imx6sabreauto_conf"
WFD_CONF_DIR_imx6dlsabresd = "wfd-sink_imx6sabresd_conf"

WFD_DEFAULT_CONFIG_mx6 = "conf_LVDS_HDMI"

WFD_PLAYER_SCRIPT_mx6 = "player_imx6.sh"

SRC_URI += "file://0001-wfd_manager-enable-automatic-screen-connection-by-de.patch \
            file://${WFD_CONF_DIR} \
"

PLAYER_SRC_mx6 = "src/player"

DEPENDS += "qtbase"

RDEPENDS_${PN} += "wayland \
                   gstreamer1.0 \
                   gstreamer1.0-plugins-base-audioconvert gstreamer1.0-plugins-base-alsa \
                   gstreamer1.0-plugins-base-volume gstreamer1.0-plugins-base-typefindfunctions \
                   gstreamer1.0-plugins-good-udp gstreamer1.0-plugins-good-audioparsers \
                   gstreamer1.0-plugins-bad-mpegtsdemux gstreamer1.0-plugins-bad-videoparsersbad \
                   imx-gst1.0-plugin \
"

inherit qmake5

S = "${WORKDIR}/git/${PLAYER_SRC}"

do_install_append () {
    # Install specific configuration files
    install -d ${D}${sysconfdir}
    install -c -m 755 ${WORKDIR}/${WFD_CONF_DIR}/${WFD_DEFAULT_CONFIG}/wfd_manager.conf ${D}${sysconfdir}
    # Install player.sh link
    install -d ${D}${bindir}
    ln -srf /usr/bin/${WFD_PLAYER_SCRIPT} ${D}${bindir}/player.sh
}

# Uninstall files from target
do_uninstall () {
    rm -f ${D}${bindir}/wfd_manager_qt

    # Remove scripts
    rm -f ${D}${bindir}/player.sh
    rm -f ${D}${bindir}/player_imx6.sh
    rm -f ${D}${bindir}/wfd_manager.sh

    # Remove wfd sink shared ressources
    rm -rf ${D}${datadir}/wfd-sink

    # Remove configuration file
    rm -f ${D}${sysconfdir}/wfd_manager.conf
}
