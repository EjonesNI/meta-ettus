FILESEXTRAPATHS_prepend := "${THISDIR}/linux-yocto-4.4:"

SRC_URI_append_sulfur = " \
		file://sulfur.scc \
		"

COMPATIBLE_MACHINE_sulfur = "sulfur"