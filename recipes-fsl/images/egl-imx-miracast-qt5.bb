DESCRIPTION = "Eurogiciel Embplug Miracast image with Qt5"
LICENSE = "MIT"

IMAGE_FSTYPES = "tar.bz2 sdcard"

require recipes-fsl/images/fsl-image-qt5-validation-imx.bb

WIRELESS_IMAGE_INSTALL = " \
    iw \
    iperf3 \
    wpa-supplicant \
    tcpdump \
    wfd-sink \
    linux-firmware-ath9k \
"

IMAGE_INSTALL += " \
    ${WIRELESS_IMAGE_INSTALL} \
"

CORE_IMAGE_EXTRA_INSTALL_remove = " \
    packagegroup-tools-bluetooth \
    packagegroup-fsl-tools-gpu \
    packagegroup-fsl-tools-gpu-external \
    packagegroup-fsl-tools-benchmark \
"
