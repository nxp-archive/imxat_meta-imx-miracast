FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://weston \
"

do_install_append() {
    install -Dm0755 ${WORKDIR}/weston ${D}${sysconfdir}/default/weston
}
