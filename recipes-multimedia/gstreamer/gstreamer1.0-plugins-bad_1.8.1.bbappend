FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Miracast specific
SRC_URI_append = " file://0001-tsdemux-add-support-for-LPCM-with-stream_type-0x83.patch \
                   file://0002-mpegtsdemux-Add-latency-optimization-algorithm.patch \
                   file://0003-Increase-faad-AAC-decoder-priority.patch \
"

PACKAGECONFIG_append = " faad"
