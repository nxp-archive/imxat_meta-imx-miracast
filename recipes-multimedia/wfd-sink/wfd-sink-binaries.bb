require wfd-sink.inc

PV = "2.26"

SRC_URI += "file://${PN}_${PV}.tar.gz"

SRC_URI[md5sum] = "65528c070bb8b60cec55ab4e9a080e09"
SRC_URI[sha256sum] = "53090a8eebc8966d3a8bd819ce8486ee500c234f743548d60d22536f178de806"

S = "${WORKDIR}/${PN}_${PV}"

inherit bin_package

DEPENDS += "qtbase"


do_install_append() {
    install -d ${D}${libdir}

    # Install wfd shared libs
    cd ${D}${libdir} && ln -sf libconfig.so.0.0.0 libconfig.so.0
    cd ${D}${libdir} && ln -sf libwpactrl.so.0.0.0 libwpactrl.so.0
}
