--
-- PostgreSQL database dump
--

-- Dumped from database version 11.4 (Debian 11.4-1.pgdg90+1)
-- Dumped by pg_dump version 11.4 (Debian 11.4-1.pgdg90+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: addresses; Type: TABLE; Schema: public; Owner: POSTGRES
--

CREATE TABLE public.addresses (
    id bigint NOT NULL,
    "Strasse" text NOT NULL,
    "Hausnummer" text NOT NULL,
    "Ort" bigint NOT NULL
);


ALTER TABLE public.addresses OWNER TO "POSTGRES";

--
-- Name: addresses_id_seq; Type: SEQUENCE; Schema: public; Owner: POSTGRES
--

CREATE SEQUENCE public.addresses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.addresses_id_seq OWNER TO "POSTGRES";

--
-- Name: addresses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: POSTGRES
--

ALTER SEQUENCE public.addresses_id_seq OWNED BY public.addresses.id;


--
-- Name: claims; Type: TABLE; Schema: public; Owner: POSTGRES
--

CREATE TABLE public.claims (
    id bigint NOT NULL,
    antragsdatum text NOT NULL,
    gewuenschtes_anfangs_datum text NOT NULL,
    bmi double precision NOT NULL,
    risikofaktor_alter bigint,
    risikofaktor_bmi bigint,
    risikofaktor_krankenhistorie bigint,
    versicherungsfaehig boolean,
    ablehnungsbegruendung text,
    versicherter_id bigint NOT NULL,
    versicherungspolice_id bigint,
    krankenhistorie_id bigint
);


ALTER TABLE public.claims OWNER TO "POSTGRES";

--
-- Name: claims_id_seq; Type: SEQUENCE; Schema: public; Owner: POSTGRES
--

CREATE SEQUENCE public.claims_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.claims_id_seq OWNER TO "POSTGRES";

--
-- Name: claims_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: POSTGRES
--

ALTER SEQUENCE public.claims_id_seq OWNED BY public.claims.id;


--
-- Name: customers; Type: TABLE; Schema: public; Owner: POSTGRES
--

CREATE TABLE public.customers (
    id bigint NOT NULL,
    eintrittdatum text NOT NULL,
    passwort text NOT NULL,
    "versicherterId" bigint
);


ALTER TABLE public.customers OWNER TO "POSTGRES";

--
-- Name: customers_id_seq; Type: SEQUENCE; Schema: public; Owner: POSTGRES
--

CREATE SEQUENCE public.customers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customers_id_seq OWNER TO "POSTGRES";

--
-- Name: customers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: POSTGRES
--

ALTER SEQUENCE public.customers_id_seq OWNED BY public.customers.id;


--
-- Name: diseases; Type: TABLE; Schema: public; Owner: POSTGRES
--

CREATE TABLE public.diseases (
    id bigint NOT NULL,
    kategorie bigint NOT NULL,
    beschreibung text NOT NULL
);


ALTER TABLE public.diseases OWNER TO "POSTGRES";

--
-- Name: diseases_id_seq; Type: SEQUENCE; Schema: public; Owner: POSTGRES
--

CREATE SEQUENCE public.diseases_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.diseases_id_seq OWNER TO "POSTGRES";

--
-- Name: diseases_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: POSTGRES
--

ALTER SEQUENCE public.diseases_id_seq OWNED BY public.diseases.id;


--
-- Name: insurancepolicies; Type: TABLE; Schema: public; Owner: POSTGRES
--

CREATE TABLE public.insurancepolicies (
    id bigint NOT NULL,
    neukunde boolean NOT NULL,
    risikozuschlag double precision,
    risikozuschlagsbegruendung text,
    monatlicher_beitrag double precision NOT NULL,
    initiale_beitragshoehe double precision NOT NULL,
    vertragsbeginn text NOT NULL,
    premium_tarif boolean NOT NULL,
    unterschriftsdatum text NOT NULL,
    police_ist_aktiv boolean NOT NULL,
    versicherter_id bigint NOT NULL,
    krankenhistorie_id bigint NOT NULL
);


ALTER TABLE public.insurancepolicies OWNER TO "POSTGRES";

--
-- Name: insurancepolicies_id_seq; Type: SEQUENCE; Schema: public; Owner: POSTGRES
--

CREATE SEQUENCE public.insurancepolicies_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.insurancepolicies_id_seq OWNER TO "POSTGRES";

--
-- Name: insurancepolicies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: POSTGRES
--

ALTER SEQUENCE public.insurancepolicies_id_seq OWNED BY public.insurancepolicies.id;


--
-- Name: insurants; Type: TABLE; Schema: public; Owner: POSTGRES
--

CREATE TABLE public.insurants (
    id bigint NOT NULL,
    "Name" text NOT NULL,
    "Vorname" text NOT NULL,
    "Geburtstag" text NOT NULL,
    "Geschlecht" text NOT NULL,
    "Groesse" double precision NOT NULL,
    "Gewicht" double precision NOT NULL,
    "Adresse" bigint NOT NULL,
    "Kundennr" bigint NOT NULL
);


ALTER TABLE public.insurants OWNER TO "POSTGRES";

--
-- Name: insurants_id_seq; Type: SEQUENCE; Schema: public; Owner: POSTGRES
--

CREATE SEQUENCE public.insurants_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.insurants_id_seq OWNER TO "POSTGRES";

--
-- Name: insurants_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: POSTGRES
--

ALTER SEQUENCE public.insurants_id_seq OWNED BY public.insurants.id;


--
-- Name: locations; Type: TABLE; Schema: public; Owner: POSTGRES
--

CREATE TABLE public.locations (
    id bigint NOT NULL,
    plz character varying(5) NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.locations OWNER TO "POSTGRES";

--
-- Name: locations_id_seq; Type: SEQUENCE; Schema: public; Owner: POSTGRES
--

CREATE SEQUENCE public.locations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.locations_id_seq OWNER TO "POSTGRES";

--
-- Name: locations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: POSTGRES
--

ALTER SEQUENCE public.locations_id_seq OWNED BY public.locations.id;


--
-- Name: medicalhistories; Type: TABLE; Schema: public; Owner: POSTGRES
--

CREATE TABLE public.medicalhistories (
    id bigint NOT NULL
);


ALTER TABLE public.medicalhistories OWNER TO "POSTGRES";

--
-- Name: medicalhistories_id_seq; Type: SEQUENCE; Schema: public; Owner: POSTGRES
--

CREATE SEQUENCE public.medicalhistories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.medicalhistories_id_seq OWNER TO "POSTGRES";

--
-- Name: medicalhistories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: POSTGRES
--

ALTER SEQUENCE public.medicalhistories_id_seq OWNED BY public.medicalhistories.id;


--
-- Name: preconditions; Type: TABLE; Schema: public; Owner: POSTGRES
--

CREATE TABLE public.preconditions (
    id bigint NOT NULL,
    krankenhistorie bigint NOT NULL,
    "Erkrankung" bigint NOT NULL
);


ALTER TABLE public.preconditions OWNER TO "POSTGRES";

--
-- Name: preconditions_id_seq; Type: SEQUENCE; Schema: public; Owner: POSTGRES
--

CREATE SEQUENCE public.preconditions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.preconditions_id_seq OWNER TO "POSTGRES";

--
-- Name: preconditions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: POSTGRES
--

ALTER SEQUENCE public.preconditions_id_seq OWNED BY public.preconditions.id;


--
-- Name: addresses id; Type: DEFAULT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.addresses ALTER COLUMN id SET DEFAULT nextval('public.addresses_id_seq'::regclass);


--
-- Name: claims id; Type: DEFAULT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.claims ALTER COLUMN id SET DEFAULT nextval('public.claims_id_seq'::regclass);


--
-- Name: customers id; Type: DEFAULT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.customers ALTER COLUMN id SET DEFAULT nextval('public.customers_id_seq'::regclass);


--
-- Name: diseases id; Type: DEFAULT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.diseases ALTER COLUMN id SET DEFAULT nextval('public.diseases_id_seq'::regclass);


--
-- Name: insurancepolicies id; Type: DEFAULT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.insurancepolicies ALTER COLUMN id SET DEFAULT nextval('public.insurancepolicies_id_seq'::regclass);


--
-- Name: insurants id; Type: DEFAULT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.insurants ALTER COLUMN id SET DEFAULT nextval('public.insurants_id_seq'::regclass);


--
-- Name: locations id; Type: DEFAULT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.locations ALTER COLUMN id SET DEFAULT nextval('public.locations_id_seq'::regclass);


--
-- Name: medicalhistories id; Type: DEFAULT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.medicalhistories ALTER COLUMN id SET DEFAULT nextval('public.medicalhistories_id_seq'::regclass);


--
-- Name: preconditions id; Type: DEFAULT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.preconditions ALTER COLUMN id SET DEFAULT nextval('public.preconditions_id_seq'::regclass);


--
-- Data for Name: addresses; Type: TABLE DATA; Schema: public; Owner: POSTGRES
--

COPY public.addresses (id, "Strasse", "Hausnummer", "Ort") FROM stdin;
1	Steinmülleralle	7b	1
\.


--
-- Data for Name: claims; Type: TABLE DATA; Schema: public; Owner: POSTGRES
--

COPY public.claims (id, antragsdatum, gewuenschtes_anfangs_datum, bmi, risikofaktor_alter, risikofaktor_bmi, risikofaktor_krankenhistorie, versicherungsfaehig, ablehnungsbegruendung, versicherter_id, versicherungspolice_id, krankenhistorie_id) FROM stdin;
1	2020-10-10T00:00:00	2020-10-11T00:00:00	22.31	\N	\N	\N	\N	\N	1	\N	1
\.


--
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: POSTGRES
--

COPY public.customers (id, eintrittdatum, passwort, "versicherterId") FROM stdin;
1	2010-10-10T00:00:00	demo	1
\.


--
-- Data for Name: diseases; Type: TABLE DATA; Schema: public; Owner: POSTGRES
--

COPY public.diseases (id, kategorie, beschreibung) FROM stdin;
1	3	Herzfehler
2	2	Raucherlunge
3	1	Schlechte Zähne
\.


--
-- Data for Name: insurancepolicies; Type: TABLE DATA; Schema: public; Owner: POSTGRES
--

COPY public.insurancepolicies (id, neukunde, risikozuschlag, risikozuschlagsbegruendung, monatlicher_beitrag, initiale_beitragshoehe, vertragsbeginn, premium_tarif, unterschriftsdatum, police_ist_aktiv, versicherter_id, krankenhistorie_id) FROM stdin;
1	f	\N	\N	35.7000000000000028	33.2000000000000028	2020-10-12T00:00:00	t	2020-10-11T00:00:00	f	1	1
\.


--
-- Data for Name: insurants; Type: TABLE DATA; Schema: public; Owner: POSTGRES
--

COPY public.insurants (id, "Name", "Vorname", "Geburtstag", "Geschlecht", "Groesse", "Gewicht", "Adresse", "Kundennr") FROM stdin;
1	Fischer	    Jens	2001-10-10T00:00:00	m	1.87000000000000011	78	1	1
\.


--
-- Data for Name: locations; Type: TABLE DATA; Schema: public; Owner: POSTGRES
--

COPY public.locations (id, plz, name) FROM stdin;
1	51643	Gummersbach
\.


--
-- Data for Name: medicalhistories; Type: TABLE DATA; Schema: public; Owner: POSTGRES
--

COPY public.medicalhistories (id) FROM stdin;
1
\.


--
-- Data for Name: preconditions; Type: TABLE DATA; Schema: public; Owner: POSTGRES
--

COPY public.preconditions (id, krankenhistorie, "Erkrankung") FROM stdin;
1	1	1
\.


--
-- Name: addresses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: POSTGRES
--

SELECT pg_catalog.setval('public.addresses_id_seq', 1, true);


--
-- Name: claims_id_seq; Type: SEQUENCE SET; Schema: public; Owner: POSTGRES
--

SELECT pg_catalog.setval('public.claims_id_seq', 1, true);


--
-- Name: customers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: POSTGRES
--

SELECT pg_catalog.setval('public.customers_id_seq', 1, true);


--
-- Name: diseases_id_seq; Type: SEQUENCE SET; Schema: public; Owner: POSTGRES
--

SELECT pg_catalog.setval('public.diseases_id_seq', 3, true);


--
-- Name: insurancepolicies_id_seq; Type: SEQUENCE SET; Schema: public; Owner: POSTGRES
--

SELECT pg_catalog.setval('public.insurancepolicies_id_seq', 1, true);


--
-- Name: insurants_id_seq; Type: SEQUENCE SET; Schema: public; Owner: POSTGRES
--

SELECT pg_catalog.setval('public.insurants_id_seq', 1, true);


--
-- Name: locations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: POSTGRES
--

SELECT pg_catalog.setval('public.locations_id_seq', 1, true);


--
-- Name: medicalhistories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: POSTGRES
--

SELECT pg_catalog.setval('public.medicalhistories_id_seq', 1, true);


--
-- Name: preconditions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: POSTGRES
--

SELECT pg_catalog.setval('public.preconditions_id_seq', 1, true);


--
-- Name: addresses addresses_pkey; Type: CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT addresses_pkey PRIMARY KEY (id);


--
-- Name: claims claims_pkey; Type: CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.claims
    ADD CONSTRAINT claims_pkey PRIMARY KEY (id);


--
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- Name: diseases diseases_pkey; Type: CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.diseases
    ADD CONSTRAINT diseases_pkey PRIMARY KEY (id);


--
-- Name: insurancepolicies insurancepolicies_pkey; Type: CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.insurancepolicies
    ADD CONSTRAINT insurancepolicies_pkey PRIMARY KEY (id);


--
-- Name: insurants insurants_pkey; Type: CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.insurants
    ADD CONSTRAINT insurants_pkey PRIMARY KEY (id);


--
-- Name: locations locations_pkey; Type: CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.locations
    ADD CONSTRAINT locations_pkey PRIMARY KEY (id);


--
-- Name: medicalhistories medicalhistories_pkey; Type: CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.medicalhistories
    ADD CONSTRAINT medicalhistories_pkey PRIMARY KEY (id);


--
-- Name: preconditions preconditions_pkey; Type: CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.preconditions
    ADD CONSTRAINT preconditions_pkey PRIMARY KEY (id);


--
-- Name: addresses fk_addresses_ort_id; Type: FK CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT fk_addresses_ort_id FOREIGN KEY ("Ort") REFERENCES public.locations(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: claims fk_claims_krankenhistorie_id_id; Type: FK CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.claims
    ADD CONSTRAINT fk_claims_krankenhistorie_id_id FOREIGN KEY (krankenhistorie_id) REFERENCES public.medicalhistories(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: claims fk_claims_versicherter_id_id; Type: FK CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.claims
    ADD CONSTRAINT fk_claims_versicherter_id_id FOREIGN KEY (versicherter_id) REFERENCES public.insurants(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: claims fk_claims_versicherungspolice_id_id; Type: FK CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.claims
    ADD CONSTRAINT fk_claims_versicherungspolice_id_id FOREIGN KEY (versicherungspolice_id) REFERENCES public.insurancepolicies(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: insurancepolicies fk_insurancepolicies_krankenhistorie_id_id; Type: FK CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.insurancepolicies
    ADD CONSTRAINT fk_insurancepolicies_krankenhistorie_id_id FOREIGN KEY (krankenhistorie_id) REFERENCES public.medicalhistories(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: insurancepolicies fk_insurancepolicies_versicherter_id_id; Type: FK CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.insurancepolicies
    ADD CONSTRAINT fk_insurancepolicies_versicherter_id_id FOREIGN KEY (versicherter_id) REFERENCES public.insurants(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: insurants fk_insurants_adresse_id; Type: FK CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.insurants
    ADD CONSTRAINT fk_insurants_adresse_id FOREIGN KEY ("Adresse") REFERENCES public.addresses(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: insurants fk_insurants_kundennr_id; Type: FK CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.insurants
    ADD CONSTRAINT fk_insurants_kundennr_id FOREIGN KEY ("Kundennr") REFERENCES public.customers(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: preconditions fk_preconditions_erkrankung_id; Type: FK CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.preconditions
    ADD CONSTRAINT fk_preconditions_erkrankung_id FOREIGN KEY ("Erkrankung") REFERENCES public.diseases(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: preconditions fk_preconditions_krankenhistorie_id; Type: FK CONSTRAINT; Schema: public; Owner: POSTGRES
--

ALTER TABLE ONLY public.preconditions
    ADD CONSTRAINT fk_preconditions_krankenhistorie_id FOREIGN KEY (krankenhistorie) REFERENCES public.medicalhistories(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

