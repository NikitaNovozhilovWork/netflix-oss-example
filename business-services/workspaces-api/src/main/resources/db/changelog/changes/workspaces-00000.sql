--
-- Name: workspace; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.workspace (
    id character varying(255) NOT NULL,
    os_family integer,
    seat integer,
    serial_number character varying(255),
    unit integer
);


ALTER TABLE public.workspace OWNER TO postgres;

--
-- Name: workspace workspace_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workspace
    ADD CONSTRAINT workspace_pkey PRIMARY KEY (id);