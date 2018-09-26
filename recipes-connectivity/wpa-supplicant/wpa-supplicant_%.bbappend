FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://wpa_supplicant.config-P2P \
                   file://wpa_supplicant.conf-P2P \
"

do_configure_append() {
    # Replace defconfig with a new configuration supporting P2P/WFD
    install -m 0755 ${WORKDIR}/wpa_supplicant.config-P2P wpa_supplicant/.config

    echo "CFLAGS +=\"-I${STAGING_INCDIR}/libnl3\"" >> wpa_supplicant/.config
    echo "DRV_CFLAGS +=\"-I${STAGING_INCDIR}/libnl3\"" >> wpa_supplicant/.config

	if echo "${PACKAGECONFIG}" | grep -qw "openssl"; then
        ssl=openssl
    elif echo "${PACKAGECONFIG}" | grep -qw "gnutls"; then
        ssl=gnutls
    fi
    if [ -n "$ssl" ]; then
        sed -i "s/%ssl%/$ssl/" wpa_supplicant/.config
    fi
}

do_install_append () {
    install -d ${D}${sysconfdir}
    install -m 600 ${WORKDIR}/wpa_supplicant.conf-P2P ${D}${sysconfdir}/wpa_supplicant.conf
    install -m 600 ${WORKDIR}/wpa_supplicant.conf-P2P ${D}${sysconfdir}/wpa_supplicant.conf.init
}
