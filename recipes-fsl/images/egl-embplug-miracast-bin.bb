DESCRIPTION = "Eurogiciel Embplug Miracast image"

LICENSE = "MIT"

inherit core-image

IMAGE_FSTYPES = " tar.bz2 sdcard"

## Select Image Features
IMAGE_FEATURES += " \
    debug-tweaks \
    splash \
    nfs-server \
    ssh-server-dropbear \
    tools-testapps \
    hwcodecs \
"

TEST_TOOLS_INSTALL = " \
    cpufrequtils i2c-tools mtd-utils imx-test \
    alsa-utils alsa-utils-speakertest evtest \
"

# Gstreamer plugins needed in case we use Gstreamer to handle MPEGTS stream
GSTREAMER_INSTALL = " \
    packagegroup-fsl-gstreamer1.0 \
"

WIRELESS_IMAGE_INSTALL = " \
    iw \
    iperf3 \
    wpa-supplicant \
    wfd-sink-binaries \
    linux-firmware-ath9k \
    tcpdump \
"

CORE_IMAGE_EXTRA_INSTALL_append = " \
    ${TEST_TOOLS_INSTALL} \
    ${WIRELESS_IMAGE_INSTALL} \
    ${GSTREAMER_INSTALL} \
"
