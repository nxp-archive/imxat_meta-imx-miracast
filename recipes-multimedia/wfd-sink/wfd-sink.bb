require wfd-sink.inc

PV = "2.25"

# BitBake uses the SRC_URI variable to point to source files regardless of their location.
# Each recipe must have a SRC_URI variable that points to the source.
SRC_URI += "git://git@bitbucket.sw.nxp.com/mss/wfd_stack_miracast.git;protocol=ssh;branch=wfd_sink_integration_v2"

#The revision of the source code used to build the package
SRCREV = "6501258d4a6cb67d916527c6cabb29255d52f031"

# Causes tarballs of the Git repositories, including the Git metadata, to be placed in the DL_DIR directory
BB_GENERATE_MIRROR_TARBALLS = "1"

SRC_URI += " file://0001-HDCP-Disable-HDCP-support-from-the-sink.patch \
             file://0002-wfd_manager-enable-automatic-screen-connection-by-de.patch \
"

DEPENDS += "wfd-sink-qt"
inherit autotools pkgconfig

S = "${WORKDIR}/git"

SSTATE_SCAN_FILES += "apxs"

CPPFLAGS_append = " -DGITID=\\"${SRCREV}\\" -DCONFIG_FILE=\\"/etc/wfd_sink.conf\\" "
