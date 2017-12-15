# basic plugins required in virtually every pipeline
RDEPENDS_${PN}-base += " \
    imx-gst1.0-plugin \
    ${@bb.utils.contains('LICENSE_FLAGS_WHITELIST', 'commercial', 'packagegroup-fslm-gstreamer1.0-commercial', '', d)} \
"
