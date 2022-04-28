package io.github.muqhc.xmltoskolloble

import org.dom4j.Attribute
import org.dom4j.Element
import org.dom4j.Namespace
import org.dom4j.io.SAXReader


fun xmlToSkolloble(xml: String): String = xmlToSkolloble(SAXReader().read(xml.byteInputStream()).rootElement)

internal fun xmlToSkolloble(element: Element, prefix: String = "", startP: String = prefix): String =
    if (element.elements().isEmpty() && element.text.isEmpty()) "$startP${genElementTag(element)} ;"
    else if (element.isTextOnly) "$startP${genElementTag(element)} - `${element.text}`"
    else if (element.isRootElement) "${genElementTag(element)}/ ${element.elements().joinToString("\n","\n") { xmlToSkolloble(it as Element, prefix) }}"
    else if (element.elements().count() == 1) "$startP${genElementTag(element)} - ${xmlToSkolloble(element.elements().first() as Element,prefix, "")}"
    else """
           |$startP${genElementTag(element)} {${element.elements().joinToString("\n","\n") { xmlToSkolloble(it as Element, "$prefix    ") }}
           |$prefix}
        """.trimMargin()

internal fun genElementTag(element: Element): String =
    "${if (!element.namespace?.prefix.isNullOrEmpty()) element.namespace.prefix+"<>" else ""}${element.name}" +
            if (genElementAttr(element).isNotEmpty()) ": "+genElementAttr(element) else ""

internal fun genElementAttr(element: Element): String =
    ((if (element.attributeCount() == 0 && element.declaredNamespaces().isEmpty()) emptyList()
    else (element.declaredNamespaces() as List<Namespace>).map {
        "xmlns${(if (it.prefix.isNotEmpty()) "<>"+it.prefix else "")}`${it.uri}`"
    }) +
    (if (element.attributeCount() == 0) emptyList()
    else (element.attributes() as List<Attribute>).map {
        (if (!it.namespace?.prefix.isNullOrEmpty()) it.namespace.prefix+"<>" else "")+it.name+"`${it.value}`"
    })).joinToString(" ")
