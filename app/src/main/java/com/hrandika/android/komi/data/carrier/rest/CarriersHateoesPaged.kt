package com.hrandika.android.komi.data.carrier.rest

import com.hrandika.android.komi.data._common.models.Links
import com.hrandika.android.komi.data._common.models.Page
import com.hrandika.android.komi.data.carrier.Carrier

data class Carriers(val carriers: List<Carrier>)
data class CarriersHateoesPaged(val page: Page, val _embedded: Carriers, val _links: Links)