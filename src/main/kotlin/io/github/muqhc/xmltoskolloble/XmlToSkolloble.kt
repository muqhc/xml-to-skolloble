package io.github.muqhc.xmltoskolloble

import org.dom4j.Attribute
import org.dom4j.Element
import org.dom4j.Namespace
import org.dom4j.io.SAXReader


fun xmlToSkolloble(xml: String): String = xmlToSkolloble(SAXReader().read(xml.byteInputStream()).rootElement)

fun xmlToSkolloble(element: Element, prefix: String = "", startP: String = prefix): String =
    if (element.elements().isEmpty() && element.text.isEmpty()) "$startP${genElementTag(element)} \\"
    else if (element.isTextOnly)
        if (element.text.split("\n").count() == 1) "$startP${genElementTag(element)} - \"${element.text}\""
        else """
           |$startP${genElementTag(element)} {${element.text.split("\n").joinToString("\n","\n") { "$prefix    \"$it\"" }}
           |$prefix}
        """.trimMargin()
    else if (element.isRootElement) "${genElementTag(element)}/ ${element.elements().joinToString("\n","\n") { xmlToSkolloble(it as Element, "$prefix    ") }}"
    else if (element.elements().count() == 1) "$startP${genElementTag(element)} - ${xmlToSkolloble(element.elements().first() as Element,prefix, "")}"
    else """
           |$startP${genElementTag(element)} {${element.elements().joinToString("\n","\n") { xmlToSkolloble(it as Element, "$prefix    ") }}
           |$prefix}
        """.trimMargin()

fun genElementTag(element: Element): String =
    "${element.name}${if (element.namespace.name != null) "@"+element.namespace.name else ""}" +
            if (genElementAttr(element).isNotEmpty()) ": "+genElementAttr(element) else ""

fun genElementAttr(element: Element): String =
    ((if (element.attributeCount() == 0) emptyList()
    else (element.declaredNamespaces() as List<Namespace>).map {
        (if (it.prefix.isNotEmpty()) it.prefix+"@" else "")+"xmlns\"${it.uri}\""
    }) +
    (if (element.attributeCount() == 0) emptyList()
    else (element.attributes() as List<Attribute>).map {
        it.name+(if (it.namespace != null) "@"+it.namespace.prefix else "")+"\"${it.value}\""
    })).joinToString(" ")
