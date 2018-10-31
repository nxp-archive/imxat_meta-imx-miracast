DESCRIPTION = "Eurogiciel Embplug Miracast image"

LICENSE = "MIT"

inherit core-image

IMAGE_FSTYPES = " tar.bz2 sdcard"

# Add nfs-server / ssh-server-dropbear / splash if required
IMAGE_FEATURES += " \
    debug-tweaks \
    hwcodecs \
"

TEST_TOOLS_INSTALL = " \
    i2c-tools mtd-utils alsa-utils alsa-utils-speakertest evtest iperf3 \
"

# Gstreamer plugins needed in case we use Gstreamer to handle MPEGTS stream
GSTREAMER_INSTALL = " \
    packagegroup-fsl-gstreamer1.0 \
    gstreamer1.0-plugins-bad-mpegtsdemux gstreamer1.0-plugins-bad-videoparsersbad \
    gstreamer1.0-plugins-bad-faad \
    gstreamer1.0-plugins-ugly-dvdlpcmdec \
    imx-gst1.0-plugin \
"

WIRELESS_IMAGE_INSTALL = " \
    iw \
    wpa-supplicant \
    init-ifupdown \
    tcpdump \
    wfd-sink \
    linux-firmware-ath9k \
"

CORE_IMAGE_EXTRA_INSTALL_append = " \
    ${TEST_TOOLS_INSTALL} \
    ${WIRELESS_IMAGE_INSTALL} \
    ${GSTREAMER_INSTALL} \
"
