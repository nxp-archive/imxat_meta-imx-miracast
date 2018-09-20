DESCRIPTION = "WFD : Wifi Display (Eurogiciel's Miracast stack) Qt UI"
SUMMARY = "WFD Sink - Wifi Display Qt UI"
HOMEPAGE = "http://www.eurogiciel.fr/fr/produits/multimedia-open-source/oss-miracast"
SECTION = "multimedia"

LICENSE = "EUROGICIEL"
LIC_FILES_CHKSUM = "file://COPYING;md5=0fe60d4e986b1d6d2e540aefa16a8f6e"

FILES_${PN} = "${bindir} "

PV = "2.25"

# BitBake uses the SRC_URI variable to point to source files regardless of their location.
# Each recipe must have a SRC_URI variable that points to the source.
SRC_URI += "git://git@bitbucket.sw.nxp.com/mss/wfd_stack_miracast.git;protocol=ssh;branch=wfd_sink_integration_v2"

#The revision of the source code used to build the package
SRCREV = "8fba6f2900c576b309ea3b439b17c272197cf825"

SRC_URI += " file://0001-HDCP-Disable-HDCP-support-from-the-sink.patch \
             file://0002-wfd_manager-enable-automatic-screen-connection-by-de.patch \
"

DEPENDS += "qtbase"
inherit qmake5

S = "${WORKDIR}/git"

do_install_append () {
    install -d ${D}${bindir}
    install -c -m 755 ${WORKDIR}/build/wfd_manager_qt ${D}${bindir}/wfd_manager_qt
}

# Uninstall files from target
do_uninstall () {
    rm -f ${D}${bindir}/wfd_manager_qt
}
