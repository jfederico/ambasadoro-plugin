package com.ambasadoro

class AmbasadoroService {

    def logParameters(Object params) {
        log.debug "----------------------------------"
        for( param in params ) log.debug "${param.getKey()}=${param.getValue()}"
        log.debug "----------------------------------"
    }

    def getCartridgeXML(ambasadoro, engineClass, endpoint = [:]) {
        def http_endpoint = "http://" + endpoint.get("server") + "/" + endpoint.get("app")
        def https_endpoint = "https://" + endpoint.get("server") + "/" + endpoint.get("app")

        def cartridge = '' +
                '<?xml version="1.0" encoding="UTF-8"?>' +
                '<cartridge_basiclti_link xmlns="http://www.imsglobal.org/xsd/imslticc_v1p0"' +
                '       xmlns:blti = "http://www.imsglobal.org/xsd/imsbasiclti_v1p0"' +
                '       xmlns:lticm ="http://www.imsglobal.org/xsd/imslticm_v1p0"' +
                '       xmlns:lticp ="http://www.imsglobal.org/xsd/imslticp_v1p0"' +
                '       xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance"' +
                '       xsi:schemaLocation = "http://www.imsglobal.org/xsd/imslticc_v1p0 http://www.imsglobal.org/xsd/lti/ltiv1p0/imslticc_v1p0.xsd' +
                '                             http://www.imsglobal.org/xsd/imsbasiclti_v1p0 http://www.imsglobal.org/xsd/lti/ltiv1p0/imsbasiclti_v1p0.xsd' +
                '                             http://www.imsglobal.org/xsd/imslticm_v1p0 http://www.imsglobal.org/xsd/lti/ltiv1p0/imslticm_v1p0.xsd' +
                '                             http://www.imsglobal.org/xsd/imslticp_v1p0 http://www.imsglobal.org/xsd/lti/ltiv1p0/imslticp_v1p0.xsd">' +
                '    <blti:title>' + ambasadoro.getTpTitle() + '</blti:title>' +
                '    <blti:description>' + ambasadoro.getTpDescription() + '</blti:description>' +
                '    <blti:launch_url>' + http_endpoint + '/' + endpoint.get("controller") + '/' + endpoint.get("action") + (ambasadoro.getId()? '/' + ambasadoro.getId(): '') + '</blti:launch_url>' +
                '    <blti:secure_launch_url>' + https_endpoint + '/' + endpoint.get("controller") + '/' + endpoint.get("action") + (ambasadoro.getId()? '/' + ambasadoro.getId(): '') + '</blti:secure_launch_url>' +
                '    <blti:icon>' + http_endpoint + '/images/' + (ambasadoro.getId()? ambasadoro.getId() + '/': '') + 'favicon.ico</blti:icon>' +
                '    <blti:secure_icon>' + https_endpoint + '/images' + (ambasadoro.getId()? '/' + ambasadoro.getId(): '') + '/favicon.ico</blti:secure_icon>' +
                '    <blti:vendor>' +
                '        <lticp:code>' + ambasadoro.getTpVendorCode() + '</lticp:code>' +
                '        <lticp:name>' + engineClass.TP_VENDOR_NAME + '</lticp:name>' +
                '        <lticp:description>' + engineClass.TP_VENDOR_DESCRIPTION + '</lticp:description>' +
                '        <lticp:url>' + engineClass.TP_VENDOR_URL + '</lticp:url>' +
                '        <lticp:contact>' +
                '            <lticp:email>' + engineClass.TP_VENDOR_CONTACT_EMAIL + '</lticp:email>' +
                '        </lticp:contact>' +
                '    </blti:vendor>' +
                '    <cartridge_bundle identifierref="BLTI001_Bundle"/>' +
                '    <cartridge_icon identifierref="BLTI001_Icon"/>' +
                '</cartridge_basiclti_link>'

        return cartridge
    }
}
