--
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee (
    id character varying(255) NOT NULL,
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    workspace_id character varying(255)
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);