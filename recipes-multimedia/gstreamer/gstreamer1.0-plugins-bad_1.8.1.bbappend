FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Miracast specific
SRC_URI_append = " file://0036-Increase-faad-AAC-decoder-priority.patch \
                   file://0037-mpegtsdemux-Latency-optimization-algorithm.patch \
                   file://0038-mpegtsdemux-Latency-optim-algo.patch \
                   file://0039-tsdemux-add-support-for-LPCM-with-stream_type-0x83.patch \
                   file://0040-tsdemux-Apply-latency-algorithm-only-for-live-stream.patch \
                   file://0041-Fix-latency-query-and-tsdemux-properties-set.patch \
                   file://0042-tsdemux-increase-the-first-buffer-count-and-correct-.patch \
"

PACKAGECONFIG_append = " faad"
