require wfd-sink.inc

PV = "2.25"

SRC_URI += "file://${PN}_${PV}.tar.gz"

SRC_URI[md5sum] = "76772c80ed929bdc6ab104c6e0266f74"
SRC_URI[sha256sum] = "803f508a3c1835e607056a368a5b4d065db63bd833059aaa01f0d5fa489a470b"

S = "${WORKDIR}/${PN}_${PV}"

inherit bin_package

DEPENDS += "qtbase"


do_install_append() {
    install -d ${D}${libdir}

    # Install wfd shared libs
    cd ${D}${libdir} && ln -sf libconfig.so.0.0.0 libconfig.so.0
    cd ${D}${libdir} && ln -sf libwpactrl.so.0.0.0 libwpactrl.so.0
}
